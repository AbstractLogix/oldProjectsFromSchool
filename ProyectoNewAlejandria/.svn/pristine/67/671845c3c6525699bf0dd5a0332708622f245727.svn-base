/* global mainApp, idWrhs, CryptoJS */

//Controlador de PopUp WhareHouse
//Por el momento tengo una Varibale  Global idWrhs encargada de neviar el valor 
//del Boton quer llama el MEtodo tiene que ser Cambiada a Variable de Session

mainApp.controller('PopUp', function($scope ,WebServicie ,$window , $sessionStorage) {
        
        //Obtenemos los Valores de Varibales de Session
        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
        
        //Variables de Totales del POPUP
        $scope.TPiezas = 0;
        $scope.TPeso = 0;
        var myData;
        
        $scope.digital = function(){
            $window.open('http://miami.cpslogistics.com:8080/IcarusDocs/reports/scanned/0'+$scope.WrhsId+'.pdf');
        }; 
        
        //Informacion de la Tabla
        $scope.gridOptions = {
            
            //Definimos Headears
            columnDefs : [
                    {field: 'Pzs',  minWidth: 55 },
                    {field: 'Pk',  minWidth: 55},
                    {field: 'Largo',  minWidth: 55},
                    {field: 'Ancho',  minWidth: 58},
                    {field: 'Alto',  minWidth: 55},
                    {field: 'Peso',  minWidth: 55}
                ],
            
            //DEfinimos data
            data: myData
        };  
            
        //Obtenemos el Detalle del WareHouse y lo Mostramos en el HTML
        WebServicie.GetDetalleWrhs(idWrhs,username,passhash+'').then(function(WR){
            
            //Webservicies Devuelve un string en formato JSON se parsea para devolver un JavaScriptObject
            var Wrhs = JSON.parse(WR);
            $scope.WrhsId = Wrhs.warehouseDetail[0].warehouseId;           
            $scope.NoPro = Wrhs.proNumber;
            $scope.Rastreo = "";
            $scope.Proveedor = Wrhs.shipper.name; 
            $scope.Cliente = Wrhs.customer.name;
            $scope.Transportista = Wrhs.inlandCarrier.name;
            $scope.Estado = Wrhs.status.name;
            $scope.Descripcion = Wrhs.description;
            $scope.Moneda = Wrhs.currency;
            $scope.ValorMerc = Wrhs.mercValue.toFixed(2);
            $scope.VolTotal = Wrhs.warehouseDetail[0].volWeight;               
            $scope.CFT = Wrhs.warehouseDetail[0].cft;
            $scope.TextoMarcas = Wrhs.marks;
            $scope.NoOrden = Wrhs.orderNo;
            $scope.NoRef = Wrhs.refNo;
            
            var F = new Date(Wrhs.initDate);
	    $scope.Fecha = (F.getMonth()+1) + "/"+F.getDate()+ "/" +  F.getFullYear() ;
            
            //Check Factura Original (Aun no Funciona)
            if(Wrhs.originalInvoice == true){$scope.CM1 = true;}
            else{$scope.CM1 = false;}
            
            //Check Factura Original(Aun no Funciona)
            if(Wrhs.packingList == true){$scope.CM2 = true;}
            else{$scope.CM2 = false;}
            
            //Check Factura Original(Aun no Funciona)
            if(Wrhs.dangerousGoods == true){$scope.CM3 = true;}
            else{$scope.CM3 = false;}
            
            //Check Factura Original(Aun no Funciona)
            if(Wrhs.packingList == true){$scope.CM4 = true;}
            else{$scope.CM4 = false;}
            
            //LLenamos la Tabla con la Info del Wrhs
            for(i = 0 ; i<Wrhs.warehouseDetail.length ; i++)
            {
             //Sumamos los Valores de la Tabla
             $scope.TPiezas = $scope.TPiezas +Wrhs.warehouseDetail[i].pieces;
             $scope.TPeso = $scope.TPeso +Wrhs.warehouseDetail[i].height;
             
             //LLenamos la Tabla con sus Valores
             $scope.gridOptions.data.push({
                'Pzs':Wrhs.warehouseDetail[i].pieces,
                'Pk': Wrhs.warehouseDetail[i].packetTypeId,
                'Largo':Wrhs.warehouseDetail[i].length,
                'Ancho': Wrhs.warehouseDetail[i].width,
                'Alto': Wrhs.warehouseDetail[i].weight,
                'Peso':Wrhs.warehouseDetail[i].height
              });          
            }
            
        });
                                
});
