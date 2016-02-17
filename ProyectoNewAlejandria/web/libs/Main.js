
//Variable Principal de la Aplicacion
//Controla todo el Sitio
var mainApp = angular.module("mainApp", [
            //Dependencias de la Aplicacion
            'ui.router',
            'ngRoute',
            'ui.grid',
            'ui.grid.edit',
            'angularSoap',
            'ngDialog',
            'ngStorage',
            'ngTouch',
            'ui.grid.selection',
            'Facturas',
            'Cargas',
            'Embarques',
            'Mensajes',
            'Historial',
            'Perfil'
                            ]);


//Factory Encargada de Los WebServicies
mainApp.factory('WebServicie', ['$soap',function($soap){
        
            //Area de Rutas de WebServicies(Luego se Cambiara por rutas Directas)
            var ExcelWS = "http://localhost:8080/ProyectoNewAlejandria/ExcelReportsWSService";
            var WarehousesWS = "http://localhost:8080/ProyectoNewAlejandria/WarehousesWSService";
            var WarehousesWS = "http://localhost:8080/ProyectoNewAlejandria/WarehousesWSService";
            var CustomersWS = "http://localhost:8080/ProyectoNewAlejandria/CustomersWSService";
            var DocumentsWS = "http://localhost:8080/ProyectoNewAlejandria/DocumentsWSService";
            var TrakingWS = "http://localhost:8080/ProyectoNewAlejandria/TrackingWSService";
            var InvoiceWS = "http://localhost:8080/ProyectoNewAlejandria/IInvoicesWSService";
            var UsersWS = "http://localhost:8080/ProyectoNewAlejandria/UsersWSService";
            var UtilyWS = "http://localhost:8080/ProyectoNewAlejandria/UtilityWSService";
            var BMWS = "http://localhost:8080/ProyectoNewAlejandria/BMWSService";
            
            return {
                
                 //WS Reporte           
                GetReportH: function(LdocsNo,LdocsType, username, authenticator){
                return $soap.post(ExcelWS,"createHistoryReport", {LdocsNo:LdocsNo, LdocsType:LdocsType,username: username, authenticator: authenticator});
                },
                
                //WS Reporte           
                GetReportF: function(id_empresa, username, authenticator){
                return $soap.post(ExcelWS,"createMovimientosAbiertosByEmpresaReport", {id_empresa: id_empresa ,username: username, authenticator: authenticator});
                },
                
                //WS Reporte           
                GetReportW: function(LwrhsId, username, authenticator){
                return $soap.post(ExcelWS,"createWarehousesReport", {LwrhsId: LwrhsId ,username: username, authenticator: authenticator});
                },
                 //WS Reporte           
                GetReportD: function(LdocsNo, LdocsType, username, authenticator){
                return $soap.post(ExcelWS,"createShippmentsReport", {LdocsNo: LdocsNo ,LdocsType:LdocsType ,username: username, authenticator: authenticator});
                },
                
               //Ws Costumer 
                GetRCostumer: function(userId,username, authenticator){
                return $soap.post(CustomersWS,"getUser", {userId : userId,username : username, authenticator :authenticator});
                },
                
                //Ws Que Lista de Provedoores
                GetProveedores: function(shippersId){
                return $soap.post(UtilyWS,"getShippersById", {shippersId:shippersId});
                },
                
                //Ws Que Regresa Mensajes del Usuario
                GetMessages: function(beginnigDate, endDate, username, authenticator){
                return $soap.post(UsersWS,"getWebUserMessages", {beginnigDate:beginnigDate, endDate:endDate ,username: username, authenticator: authenticator});
                },
                
                //WS que Invoca WebServicie Prueba
                GetWarehouse: function(username, authenticator){
                return $soap.post(WarehousesWS,"getWarehousesOnOHRTLCFCCSByCustomer", {username: username, authenticator: authenticator});
                },
                
                //WS que Regresa WebUSer
                GetWebUser: function(username, authenticator){
                return $soap.post(CustomersWS,"getCustomer", {username: username, authenticator: authenticator});
                },
                
                //WS que regresa Wrhs*Id            
                GetDetalleWrhs: function(warehouseId,username, authenticator){
                return $soap.post(WarehousesWS,"getWarehouse", {warehouseId: warehouseId ,username: username, authenticator: authenticator});
                },
                
                //WS que regresa Documentos del Usuario          
                GetDocuments: function(username, authenticator){
                return $soap.post(DocumentsWS,"getDocumentsOnTransitByCustomer", {username: username, authenticator: authenticator});
                },
           
                //WS que regresa Documentos del Usuario          
                GetHDocuments: function(username, authenticator){
                return $soap.post(DocumentsWS,"getDeliveredDocumentsByCustomer", {username: username, authenticator: authenticator});
                },
                //WS que regresa Traking del Documento          
                GetTrakingD: function(documentNo,username, authenticator){
                return $soap.post(TrakingWS,"getTrackingByDocument", {documentNo: documentNo ,username: username, authenticator: authenticator});
                },
                
                //WS que regresa Traking del Documento          
                GetDocumentWrhs: function(documentNo, documentType ,username, authenticator){
                return $soap.post(DocumentsWS,"getDocumentWarehouses", {documentNo: documentNo , documentType: documentType ,username: username, authenticator: authenticator});
                },
                
                 //WS que regresa Facturas del Documento          
                GetInvoiceD: function(documentNo, documentType ,username, authenticator){
                return $soap.post(InvoiceWS,"getDocumentInvoices", {documentNo: documentNo , documentType: documentType ,username: username, authenticator: authenticator});
                },
                
                //Regresa Info Docu*S*N        
                GetFacD: function(serie, number ,username, authenticator){
                return $soap.post(DocumentsWS,"getInvoiceDocuments", {serie: serie , number: number ,username: username, authenticator: authenticator});
                },
                
                GetInovoiceSN: function(serie, number ,username, authenticator){
                return $soap.post(InvoiceWS,"getInvoiceBySerieNumber", {serie: serie, number: number,username: username, authenticator: authenticator});
                },
                
                //Ws Llena la Tabla de Facturas
                GetBMWS: function(idempresa ,username, authenticator){
                return $soap.post(BMWS,"getMovimientosAbiertosByEmpresa", {idempresa: idempresa ,username: username, authenticator: authenticator});
                },
                
                GetDownload: function(serie, number ,username, authenticator){
                return $soap.post(InvoiceWS,"getPrintFtpInvoice", {serie: serie, number: number,username: username, authenticator: authenticator});
                }
                
            };
}]);


//Se maneja el objeto Wiew en el Html para configurar las difentes Opciones del Menu
mainApp.config(['$routeProvider',
         function($routeProvider ) {
            $routeProvider.
               when('/MiCarga', {
                  templateUrl: 'partials/Micarga.html',
                  controller: 'MiCargaController' //CargaController.js
               }).
               when('/MisEmbarques', {
                  templateUrl: 'partials/MisEmbarques.html',
                  controller: 'MiEmbarqueController'//EmbarquesController.js
               }).
               when('/MisFacturas', {
                  templateUrl: 'partials/MisFacturas.html',
                  controller: 'MiFacturas'//EmbarquesController.js
               }).
               when('/TabPerfil', {
                  templateUrl: 'partials/TabPerfil.html',
                  controller: 'TP'
               }).
               when('/TabConf', {
                  templateUrl: 'partials/TabConfiguracion.html',
                  controller: 'TC'
               }).
               when('/TabContactos', {
                  templateUrl: 'partials/TabContactos.html',
                  controller: 'TCon'
               }).
               when('/TabCContraseña', {
                  templateUrl: 'partials/TabCContraseña.html',
                  controller: 'TCCon'
               }).
               when('/Historial', {
                  templateUrl: 'partials/Historial.html',
                  controller: 'HistorialController'
               }).
               when('/MisMensajes', {
                  templateUrl: 'partials/MisMensajes.html',
                  controller: 'MensajesController'
               });
}]);
         
//ContraLador Encargado de Almacenar la Informacion Inicial del Sistema
mainApp.controller('CargarDatos', function($scope,WebServicie ,$sessionStorage){
            
            
                           var now = new Date();
		var nowN = now.getTime();
		if (now.getDay() === 0) {
			var cocETAN = nowN+(2*24*60*60*1000);
			var airETAN= nowN+(3*24*60*60*1000);
		} else if (now.getDay() === 4) {
			cocETAN = nowN+(1*24*60*60*1000);
			airETAN = nowN+(4*24*60*60*1000);
		} else if (now.getDay() === 5) {
			cocETAN = nowN+(3*24*60*60*1000);
			airETAN = nowN+(4*24*60*60*1000);
		} else if (now.getDay() === 6) {
			cocETAN = nowN+(3*24*60*60*1000);
			airETAN = nowN+(4*24*60*60*1000);
		} else {
			cocETAN = nowN+(1*24*60*60*1000);
			airETAN = nowN+(2*24*60*60*1000);
		}
                
		var lclETAN = nowN+(12*24*60*60*1000);	
		var cocETAD = new Date(cocETAN);
		var airETAD = new Date(airETAN);
		var lclETAD = new Date(lclETAN);
		$sessionStorage.airETAL = airETAD; 
		$sessionStorage.cocETAL = cocETAD;
		$sessionStorage.lclETAL = lclETAD;
                
                
            //Estas Variables Vienen del Login(Por motivos de prueba las tengo asi)
            //Convertimos el Password
            $sessionStorage.user = "TECNOBODEGA4";
            $sessionStorage.pass = "123456";
            var username = "TECNOBODEGA4";
            var password = "123456";
            var passhash = CryptoJS.MD5(username+password);
            
                
                //Encontramos el Nombre de la Empresa
                WebServicie.GetWebUser(username,passhash+'').then(function(User){
                    var IUser = JSON.parse(User);
                    $scope.Nombre = IUser.name;
                    $sessionStorage.cliente = IUser;
                });
            
                //LLenamos el Json de los Wrhs
                //Pasamos los datos por el WebServicie
                WebServicie.GetWarehouse(username,passhash+'').then(function(WR){
                    
                    var Wrhs = JSON.parse(WR);
                    $sessionStorage.Lwrhs = Wrhs;
                });   
                
                //LLenamos el Json de los Cocumentos
                //Pasamos los datos por el WebServicie
                WebServicie.GetDocuments(username,passhash+'').then(function(Doc){
                    var Docu = JSON && JSON.parse(Doc) || $.parseJSON(Doc);
                    $sessionStorage.Ldocumentos = Docu;
                });
            
            
});
            
         
       