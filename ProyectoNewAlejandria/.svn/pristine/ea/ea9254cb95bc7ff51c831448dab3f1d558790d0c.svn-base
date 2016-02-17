/* global mainApp, CryptoJS, NoFac, SerFac */

//Controlador de PopUp Factura
//Por el momento tengo una Varibale  Global NoFac y SerFac
//ser Cambiada a Variable de Session

mainApp.controller('PopUpF', function($scope ,WebServicie ,$sessionStorage ,uiGridConstants) {
   
        //Info
        var username = $sessionStorage.user+'';
        var password = $sessionStorage.pass+'';
        var passhash = CryptoJS.MD5(username+password);
        $scope.Total = 0;
        
        //Informacion de la Tabla
        var myData;
        $scope.gridOptions = {
            enableRowSelection: true,
            enableRowHeaderSelection: false, 
           
            //Definimos Headears
            columnDefs : [
                    {field: 'Cant',  minWidth: 70, width: 70},
                    {field: 'Descripcion',  minWidth: 70, width: 360},
                    {field: 'Documento',  minWidth: 70, width: 160},
                    {field: 'Total' ,  minWidth: 70, width: 100}
                ],
            
            //DEfinimos data
            data: myData
        };  
        $scope.gridOptions.multiSelect = false;
        
        //Llenamos el Info
        WebServicie.GetInovoiceSN(SerFac,NoFac,username,passhash+'').then(function(Factura){
            
            var Fac = JSON.parse(Factura);
            if(Fac != null){
                $scope.Serie = Fac.serie;
                $scope.Numero = Fac.number;
                $scope.Fecha = Fac.printDate;
                $scope.Balance = "???";
                $scope.Nombre = Fac.billingName;
                $scope.Nit = Fac.billingNo;
                $scope.Direccion = Fac.billingAddress;
                
                var Moneda = "$"
                if( Fac.currency == "QUE"){ Moneda = "Q";}
                
                //LLenamos Tbla
                for(var i = 0; i < Fac.detail.length ; i++)
                {
                     $scope.gridOptions.data.push({
                         'Cant' : Fac.detail[i].quantity,
                         'Descripcion' : Fac.detail[i].description,
                         'Documento' : Fac.detail[i].documentNo,
                         'Total' :Moneda + Fac.detail[i].total
                     });
                     
                     $scope.Total = $scope.Total + Fac.detail[i].total;
                }
                
                $scope.Moneda = Moneda;
            }
                       
        });
});