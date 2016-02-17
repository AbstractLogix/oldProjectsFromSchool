/* global mainApp, idDocu, DocuT, CryptoJS */

//Controlador de PopUp Document
//Por el momento tengo una Varibale  Global idDocu 
//ser Cambiada a Variable de Session

mainApp.controller('PopUpD', function($scope ,WebServicie ,$sessionStorage ,uiGridConstants) {
        
        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
        var tipo;
        
        var myData;
        //Informacion de la Tabla
        $scope.gridOptions = {
            enableRowSelection: true,
            enableRowHeaderSelection: false, 
           
            //Definimos Headears
            columnDefs : [
                    {field: 'Serie',  minWidth: 70, width: 80},
                    {field: 'Numero',  minWidth: 70, width: 150},
                    {field: 'Total',  minWidth: 70, width: 90},
                    {field: 'Index' , visible: false}
                ],
            
            //DEfinimos data
            data: myData
        };  
        $scope.gridOptions.multiSelect = false;
    
        var myData2;
        //Informacion de la Tabla
        $scope.gridOptions2 = {
            enableRowSelection: true,
            enableRowHeaderSelection: false, 
            
            //Definimos Headears
            columnDefs : [
                    {field: 'Cant',  minWidth: 70, width: 80},
                    {field: 'Descripción',  minWidth: 70, width: 150},
                    {field: 'Total',  minWidth: 70, width: 90}
                ],
            
            //DEfinimos data
            data: myData2
        };  
        $scope.gridOptions2.multiSelect = false;
        
        $scope.NoDocu = idDocu;
        
        //LLenamos la info del Documento
        for(var i = 0; i<$sessionStorage.Ldocumentos.length; i++)
        {
            if($sessionStorage.Ldocumentos[i].documentNo == idDocu)
            {
                $scope.Fecha =  $sessionStorage.Ldocumentos[i].documentDate;
                $scope.Origen = $sessionStorage.Ldocumentos[i].origenCityCode;
                $scope.Destination = $sessionStorage.Ldocumentos[i].destinationCityCode;
                $scope.Cliente = $sessionStorage.cliente.name;
                $scope.Des = $sessionStorage.Ldocumentos[i].description;
                $scope.Ruta = $sessionStorage.Ldocumentos[i].routingId;
                $scope.Moneda = "???";
                $scope.ValorM = $sessionStorage.Ldocumentos[i].mercValue;
                $scope.Piezas = $sessionStorage.Ldocumentos[i].totalPieces;
                $scope.Peso = $sessionStorage.Ldocumentos[i].totalWeight;
                $scope.PesoV = $sessionStorage.Ldocumentos[i].totalVolWeight ;
                $scope.CFT = $sessionStorage.Ldocumentos[i].totalCft ;
                $scope.MP = $sessionStorage.Ldocumentos[i].dangerousGoods;
                tipo = $sessionStorage.Ldocumentos[i].documentType;
                $scope.Tipo = tipo;
            }
        }
        
        var Temp;
        //LLenamos la Tabla1
        WebServicie.GetInvoiceD(idDocu,tipo,username,passhash+'').then(function(LF){
            var LFJ = JSON.parse(LF);
            Temp = LFJ;
             for(var i = 0 ; i< LFJ.length ; i++)
            {
                $scope.gridOptions.data.push({
                    'Serie':LFJ[i].serie,
                    'Numero': LFJ[i].number,
                    'Total':LFJ[i].totalDollars,
                    'Index': i
                });     
            }
        }); 
        
        
        //Parte Encargada de Sincronisar fila seleccionada para recuperar el valor
        $scope.gridOptions.onRegisterApi = function( gridApi ) {
            $scope.gridApi = gridApi;
        
            $scope.toggleRowSelection = function() {
                $scope.gridApi.selection.clearSelectedRows();
                $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
                $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
            };
        
        };
        
      
         
        //Agrega listado a Tabla2
        $scope.selectRow = function(){
            
            var p = $scope.gridApi.selection.getSelectedRows();
            var id = p[0].Index;
            
            $scope.Total = 0;
            $scope.gridOptions2.data= [];
                                       
            for(var j = 0; j<Temp[id].detail.length ; j++){      
                        $scope.gridOptions2.data.push({    
                            'Cant':Temp[id].detail[j].quantity,
                            'Descripción': Temp[id].detail[j].description,
                            'Total':Temp[id].detail[j].totalDollars
                        });
                        
               $scope.Total = $scope.Total + parseInt(Temp[id].detail[j].totalDollars);
            }
                           
        };
});

