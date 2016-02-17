/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* globalCryptoJS ,idWrhs ,idDocu*/

var appEmbarque = angular.module("Embarques", [
            'ui.grid',
            'ui.grid.edit',
            'ngDialog',
            'ngStorage',
            'ngTouch',
            'ui.grid.selection',
]);

appEmbarque.controller('MiEmbarqueController', function($scope) {

});
         
appEmbarque.controller('TableEmbarque', function($scope,  $http, ngDialog ,WebServicie ,$sessionStorage ,uiGridConstants ,$filter , $window) {
        
        $scope.Piezas = 0;
        $scope.Ctf = 0;
        $scope.Peso = 0;
        $scope.PesoV = 0;
        $scope.Medida = "lbs";
        $scope.SItem = [{}];  //Valor Donde se Alamcena la Fila Seleccionada
         
        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
         
         //Encargado de Exportar Tabla
        $scope.Exportar = function(){
            
            var NW = "";
            var NT = "";
            if($scope.gridOptions.data.length == 0){
                    sweetAlert("", "No hay Datos Para Exportar", "error");
            }
            else{
                    for(var i = 0; i<$scope.gridOptions.data.length ; i++){  
                        NW += $scope.gridOptions.data[i].Documento+ ',';
                        NT += $scope.gridOptions.data[i].Tipo+ ',';
                    }
            
                    swal({   title: "Generando",   text: "Espere un Momento",   timer: 120000,   showConfirmButton: false });
                    WebServicie.GetReportD(NW,NT,username,passhash+'').then(function(User){
                        var a = '<a class="ghostb" href="/ProyectoNewAlejandria/reports/excel/'+User+'_shippmentsReport.xls"  target="_self">Descargar</a>';
                        sweetAlert({title: '' ,text:a , confirmButtonText: "Cerrar" ,html:true });   
                    });
            }
            
        };
        
        //Encargado de Abrir Pdf del Wrhs
        $scope.digital = function(Wrhsid){
            $window.open('http://miami.cpslogistics.com:8080/IcarusDocs/reports/scanned/0'+Wrhsid+'.pdf');
        }; 
         
        var myData =[{ }];
        var myData2;
        
        $scope.Open = function (id) {            
            idWrhs = id;
            ngDialog.open({
                templateUrl: 'partials/PopUpWrhs.html',
                controller: "PopUp"
            });
        
        };
        
        $scope.OpenD = function (id) {   
            idDocu = id;
            ngDialog.open({
                templateUrl: 'partials/PopUpDocument.html',
                controller: "PopUpD",
                className: 'ngdialog-theme-plain'
                
            });
        };
        
        
        //Informacion de Tabla 1
        $scope.gridOptions = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,        
            selectedItems: $scope.mySelections,
            useExternalFiltering: true,
            
            //Definimos Headears
            columnDefs : [
                {name:'Documento', minWidth: 70, width: 110,enableFiltering: false,
                cellTemplate:'<a class="ghost" style="width:100%;height:100%" ng-click= grid.appScope.OpenD(row.entity.Documento)>'+
                                '{{row.entity.Documento}}'+
                                '</a>'},
                {name:'Ruta',  minWidth: 70, width: 70},
                {name:'Orig',  minWidth: 70, width: 50 , enableFiltering: false}, 
                {name: 'Dest',  minWidth: 70, width: 60 , enableFiltering: false},
                {name: 'Pzs',  minWidth: 70, width: 44 , enableFiltering: false},
                {name: 'Peso',  minWidth: 70, width: 80 , enableFiltering: false},
                {name: 'Eta',  minWidth: 70, width: 65 , enableFiltering: false},
                {name: 'Tipo' , visible: false , enableFiltering: false}
            ],
            
            //DEfinimos data
            data: myData
        }; 
        $scope.gridOptions.multiSelect = false;
        
        //Informacion de Tabla 2
        $scope.gridOptions2 = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,        
            selectedItems: $scope.mySelections,
            useExternalFiltering: true,
            
            //Definimos Headears
            columnDefs : [
                {name:'WrhId', displayName: 'Wrhs ID' , minWidth: 70, width: 80,
                cellTemplate:'<a class="ghost" style="width:100%;height:100%" ng-click= grid.appScope.Open(row.entity.WrhId)>'+
                                '{{row.entity.WrhId}}'+
                                '</a>'},
                {name:'OCompra',displayName: 'Orden de Compra' , minWidth: 70, width: 170},
                {name:'Accion', displayName: '' , minWidth: 70, width: 80,
                cellTemplate:'<a class="ghost" style="width:100%;height:100%" ng-click= grid.appScope.digital(row.entity.WrhId)> '+
                                'Digital'+
                                '</a>'} 
            ],
            
            //DEfinimos data
            data: myData2
        };  
        $scope.gridOptions2.multiSelect = false;
        
        $scope.Filtro = function() {

            var filtro = "";
            if($scope.searchText === "Aerea"){filtro = "AIR";}
            if($scope.searchText === "Courier"){filtro = "COC";}
            if($scope.searchText === "Box"){filtro = "BOX";}
            if($scope.searchText === "LCL"){filtro = "LCL";}
            if($scope.searchText === "FCL"){filtro = "FCL";}
            
            //Iniciamos a 0 Totales
                $scope.Piezas = 0;
                $scope.Ctf = 0;
                $scope.Peso = 0;
                $scope.PesoV = 0;
                
            $scope.gridOptions.data = [];
            
                for(i = 0 ; i<$sessionStorage.Ldocumentos.length ; i++)
                {
                                         
                    if( $sessionStorage.Ldocumentos[i].routingId == filtro || filtro == "")
                    {
                        //Sumamos los Valores de la Tabla
                        $scope.Piezas = $scope.Piezas + parseInt($sessionStorage.Ldocumentos[i].totalPieces);
                        $scope.Ctf = $scope.Ctf + parseInt($sessionStorage.Ldocumentos[i].totalCft);
                        $scope.Peso = $scope.Peso + parseInt($sessionStorage.Ldocumentos[i].totalWeight);
                        $scope.PesoV = $scope.PesoV + parseInt($sessionStorage.Ldocumentos[i].totalVolWeight);
                        $scope.Medida = $sessionStorage.Ldocumentos[i].measure;
                    
                        //LLenamos la Tabla con sus Valores
                        $scope.gridOptions.data.push({
                            'Documento':$sessionStorage.Ldocumentos[i].documentNo,
                            'Ruta': $sessionStorage.Ldocumentos[i].routingId,
                            'Orig':$sessionStorage.Ldocumentos[i].origenCityCode,
                            'Dest':$sessionStorage.Ldocumentos[i].destinationCityCode,
                            'Pzs':$sessionStorage.Ldocumentos[i].totalPieces,
                            'Peso':$sessionStorage.Ldocumentos[i].totalWeight,
                            'Eta': "---",
                            'Tipo': $sessionStorage.Ldocumentos[i].documentType
                        });     
                    }
                }
                      
        };
        
        //Pasamos los datos por el WebServicie
        $scope.gridOptions.data.splice(0,1); 
        for(i = 0 ; i<$sessionStorage.Ldocumentos.length ; i++)
        {
             //Sumamos los Valores de la Tabla
             $scope.Piezas = $scope.Piezas + parseInt($sessionStorage.Ldocumentos[i].totalPieces);
             $scope.Ctf = $scope.Ctf + parseInt($sessionStorage.Ldocumentos[i].totalCft);
             $scope.Peso = $scope.Peso + parseInt($sessionStorage.Ldocumentos[i].totalWeight);
             $scope.PesoV = $scope.PesoV + parseInt($sessionStorage.Ldocumentos[i].totalVolWeight);
             $scope.Medida = $sessionStorage.Ldocumentos[i].measure;
             
             //LLenamos la Tabla con sus Valores
             $scope.gridOptions.data.push({
                'Documento':$sessionStorage.Ldocumentos[i].documentNo,
                'Ruta': $sessionStorage.Ldocumentos[i].routingId,
                'Orig':$sessionStorage.Ldocumentos[i].origenCityCode,
                'Dest':$sessionStorage.Ldocumentos[i].destinationCityCode,
                'Pzs':$sessionStorage.Ldocumentos[i].totalPieces,
                'Peso':$sessionStorage.Ldocumentos[i].totalWeight,
                'Eta': "---",
                'Tipo': $sessionStorage.Ldocumentos[i].documentType
              });          
            }
        
        
        //Parte Encargada de Sincronisar fila seleccionada para recuperar el valor
        $scope.gridOptions.onRegisterApi = function( gridApi ) {
        $scope.gridApi = gridApi;
        
        $scope.toggleRowSelection = function() {
            $scope.gridApi.selection.clearSelectedRows();
            $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
            $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
        };
        
        
        //Metodo Cuando se Selecciona una Fila
        $scope.selectRow = function(){
            var p = $scope.gridApi.selection.getSelectedRows();
            
            var username = "TECNOBODEGA4";
            var password = "123456";
            var passhash = CryptoJS.MD5(username+password);
            
            
            //Aki llenamos la Tabla Warehouse del Documento primero Encontramos el Documento
            $scope.gridOptions2.data = [];
            WebServicie.GetDocumentWrhs(p[0].Documento, p[0].Tipo, username,passhash+'').then(function(Lista){
                    var LISTAJ = JSON.parse(Lista);
                    //Aki LLenamos el Traking
                    for(var i = 0; i<LISTAJ.length ; i++)
                    {
                         $scope.gridOptions2.data.push({
                             'WrhId': LISTAJ[i].id,
                             'OCompra': LISTAJ[i].orderNo,
                             'Accion': ""
                             
                         });
                    }
            });   
                
        
            var TrakingF = [];
            TrakingF.push("Fecha");
            var TrakingM = [];
            TrakingM.push("Mensaje");
            WebServicie.GetTrakingD(p[0].Documento , username , passhash+'').then(function(DT){
                var DTJ = JSON.parse(DT);  
                for(var i = 0; i < DTJ.length ; i++)
                {
                    TrakingF.push(DTJ[i].trackDate.substring(0,12));
                    TrakingM.push(DTJ[i].message);
                }
            });
            $scope.TF = TrakingF;
            $scope.TM = TrakingM;
            
        };

  };
       
    

});
