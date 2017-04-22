SET DATEFIRST 7
SET ANSI_NULLS OFF
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
SET LOCK_TIMEOUT -1
SET QUOTED_IDENTIFIER OFF
GO


Use
MiPortal
GO


--DROP TABLE Usuario
---- ******** Usuario ********
if Not exists (select * from sysobjects where id = object_id('dbo.Usuario') and type = 'U') 

CREATE TABLE dbo.Usuario (
Usuario	varchar(15),
Nombre	varchar(100),
Contrasena	varchar(32),
ContrasenaConfirmacion	varchar(32),
Estatus	varchar(15),
UsuarioTipo	varchar(20)
CONSTRAINT priUsuario PRIMARY KEY CLUSTERED (Usuario))

GO

--DROP TABLE Prov
---- ******** Prov ********
if Not exists (select * from sysobjects where id = object_id('dbo.Prov') and type = 'U') 

CREATE TABLE dbo.Prov (
Nombre		varchar(100),
RFC			varchar(15),
Direccion	varchar(100),
Direccionnumero	varchar(20),
Direccionnumerointerior	varchar(20),
Colonia	varchar(100),
Poblacion	varchar(100),
Estado	varchar(30),
Pais	varchar(30),
CodigoPostal	varchar(15),
Telefono	varchar(100),	
Contacto	varchar(50),
eMail		varchar(50),	
Estatus	    varchar(15),
Contrasena	varchar(32),
ContrasenaConfirmacion	varchar(32),
CONSTRAINT priProv PRIMARY KEY CLUSTERED (RFC))

GO

--DROP TABLE Documento
---- ******** Documento ********
if Not exists (select * from sysobjects where id = object_id('dbo.Documento') and type = 'U') 

CREATE TABLE dbo.Documento (
ID			int IDENTITY(1,1) NOT NULL,
Documento	varchar(max),
NombreDocumento varchar(250),
Ruta		varchar(500),
RFC			varchar(15),
Importe		money,
FechaTimbrado	datetime,
UUID		uniqueidentifier,
FechaAlta	datetime,
Estatus		varchar(15),
ClabeInterbancaria varchar(20),
FormaPago	varchar(50),
FechaPago	datetime
CONSTRAINT priDocumento PRIMARY KEY CLUSTERED (ID))

GO


--DROP TABLE TempDocumento
if Not exists (select * from sysobjects where id = object_id('dbo.TempDocumento') and type = 'U') 

CREATE TABLE dbo.TempDocumento (
ID	int IDENTITY(1,1) NOT NULL,
Documento	varchar(max),
NombreDocumento varchar(250),
Ruta		varchar(500)
CONSTRAINT priTempDocumento PRIMARY KEY CLUSTERED (ID))

GO


--Trigger

/************** Usuarios **************************/
--Tiene la finalidad de relizar el registro de los proveedores a la tabla usuarios.


if exists (select * from sysobjects where id = object_id('dbo.TgProv') and sysstat & 0xf = 8) drop trigger dbo.TgProv
GO

CREATE TRIGGER [dbo].[TgProv] on [dbo].[Prov] 
   AFTER INSERT, UPDATE     
AS 

BEGIN	
   DECLARE
   @UsuarioR	varchar(15), 
   @NombreR		varchar(100),  
   @ContrasenaR	varchar(32),   
   @ContrasenaConfirmacionR varchar(32),   
   @EstatusR	varchar(15), 
   @Contrasena	varchar(32),   
   @ContrasenaConfirmacion varchar(32)

    
    Select 
      @UsuarioR = RFC,
      @NombreR = Nombre,
      @ContrasenaR = Contrasena,
      @ContrasenaConfirmacionR = ContrasenaConfirmacion,
      @EstatusR = Estatus 
	From inserted
	
	Select 
      @Contrasena = Contrasena,
      @ContrasenaConfirmacion = ContrasenaConfirmacion
    From deleted
		

   IF @ContrasenaR <> @Contrasena  OR @ContrasenaConfirmacionR<>@ContrasenaConfirmacion 
    BEGIN
	    IF @UsuarioR IS NOT NULL
	    BEGIN 
              INSERT INTO Usuario ( Usuario,  Nombre,  Contrasena,   ContrasenaConfirmacion,   Estatus,   UsuarioTipo) 
		  		   	       VALUES (@UsuarioR,@NombreR,@ContrasenaR, @ContrasenaConfirmacionR, @EstatusR, 'Proveedor')
		    if @@ERROR <> 0
		       print @@ERROR 
	    END    
   END		                  
	
	                	                              
END
GO

			
			
			
--Procedimiento que lee un XML y obtiene valores como Importe, ReceptorRFC, EmisorRFC y UUID
if exists (select * from sysobjects where id = object_id('dbo.xpLeeXML') and type = 'P') drop procedure dbo.xpLeeXML
GO

CREATE PROCEDURE xpLeeXML
@IDDocumento   int,              --ID del documento
@Campo   char(20),               --Campo que se dese leer, puede ser: Importe,ReceptorRFC,EmisorRFC,UUID
@Resultado varchar(255) OUTPUT   --Valor del campo leido que se devuelve

AS BEGIN

DECLARE
@EmisorRFC varchar(50), 
@ReceptorRFC varchar(50),
@Importe float,
@UUID varchar(50),
@XML varchar(max),
@DocumentoXML xml,
@PrefijoCFDI varchar(255),
@RutaCFDI varchar(255),
@iDatos int,
@FechaTimbrado	char(20)

--SELECT A LA TABLA CON EL DOCUMENTO XML
 SELECT
      @XML = Documento
      FROM TempDocumento
     WHERE 
     --Modulo = 'VTAS' 
       --AND 
      ID = @IDDocumento
      
    SET @DocumentoXML = CONVERT(XML,@XML)
    SET @PrefijoCFDI = '<ns xmlns' + CHAR(58) + 'cfdi="http' + CHAR(58) + '//www.sat.gob.mx/cfd/3" xmlns' + CHAR(58) + 'tfd="http' + CHAR(58) + '//www.sat.gob.mx/TimbreFiscalDigital"/>'    
    EXEC sp_xml_preparedocument @iDatos OUTPUT, @DocumentoXML, @PrefijoCFDI


SET @Resultado=NULL

SET @RutaCFDI = '/cfdi' + CHAR(58) + 'Comprobante'    

SELECT  
@Importe = total 
FROM OPENXML (@iDatos, @RutaCFDI, 1) WITH (total float)

SET @RutaCFDI = '/cfdi' + CHAR(58) + 'Comprobante/cfdi' + CHAR(58) + 'Receptor'
SELECT  
@ReceptorRFC = rfc 
FROM OPENXML (@iDatos, @RutaCFDI, 1) WITH (rfc varchar(15))

SET @RutaCFDI = '/cfdi' + CHAR(58) + 'Comprobante/cfdi' + CHAR(58) + 'Emisor'
SELECT  
@EmisorRFC = rfc 
FROM OPENXML (@iDatos, @RutaCFDI, 1) WITH (rfc varchar(15))

SET @RutaCFDI = '/cfdi' + CHAR(58) + 'Comprobante/cfdi' + CHAR(58) + 'Complemento/tfd' + CHAR(58) + 'TimbreFiscalDigital'
SELECT  
@FechaTimbrado= FechaTimbrado
FROM OPENXML (@iDatos, @RutaCFDI, 1) WITH (FechaTimbrado char(20))


SET @RutaCFDI = '/cfdi' + CHAR(58) + 'Comprobante/cfdi' + CHAR(58) + 'Complemento/tfd' + CHAR(58) + 'TimbreFiscalDigital'
SELECT  
@UUID = UUID
FROM OPENXML (@iDatos, @RutaCFDI, 1) WITH (UUID uniqueidentifier)

EXEC sp_xml_removedocument @iDatos  


IF @Campo='Importe'
   SET @Resultado=@Importe


IF @Campo='ReceptorRFC'
   SET @Resultado=@ReceptorRFC


IF @Campo='EmisorRFC'
   SET @Resultado=@EmisorRFC
   
IF @Campo='FechaTimbrado'
   SET @Resultado=@FechaTimbrado


IF @Campo='UUID'
   SET @Resultado=@UUID


Select @Resultado

END
GO
			