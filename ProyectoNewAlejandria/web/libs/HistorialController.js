var appHistorial = angular.module("Historial", [
            'ui.grid',
            'ui.grid.edit',
            'ngDialog',
            'ngStorage',
            'ngTouch',
            'ui.grid.selection'   
]);

appHistorial.controller('HistorialController', function($scope) {
    $scope.message = "COntrolador Historia";
});
         
appHistorial.controller('TableHistorial', function($scope,  ngDialog ,WebServicie ,$sessionStorage ,uiGridConstants ,$filter , $window) {
        
        
        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
        
        $scope.Piezas = 0;
        $scope.Ctf = 0;
        $scope.Peso = 0;
        $scope.PesoV = 0;
        $scope.Medida = "lbs";
        $scope.SItem = [{}];  //Valor Donde se Alamcena la Fila Seleccionad
        $scope.CM1 = true;
        $scope.searchText = 0;
        
        $scope.com1 = [{'value': 'Todos'},{'value': 'Aerea'},{'value': 'Courier'},{'value': 'Box'},{'value': 'LCL'} , {'value': 'FCL'}];
        //Listado de Shippers
        var LS = [];
        var LNS = [];
         
         
        //Encargado de Abrir Pdf del Wrhs
        $scope.digital = function(Wrhsid){
            $window.open('http://miami.cpslogistics.com:8080/IcarusDocs/reports/scanned/0'+Wrhsid+'.pdf');
        }; 
        
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
                    WebServicie.GetReportH(NW,NT,username,passhash+'').then(function(User){
                        var a = '<a class="ghostb" href="/ProyectoNewAlejandria/reports/excel/'+User+'_historyReport.xls"  target="_self">Descargar</a>';
                        sweetAlert({title: '' ,text:a , confirmButtonText: "Cerrar" ,html:true });   
                    });
            }
            
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
                {name:'Documento', minWidth: 110,enableFiltering: false,
                cellTemplate:'<a class="ghost" style="width:100%;height:100%" ng-click= grid.appScope.OpenD(row.entity.Documento)>'+
                                '{{row.entity.Documento}}'+
                                '</a>'},
                {name:'Ruta',  minWidth: 70},
                {name:'Orig',  minWidth: 50, enableFiltering: false}, 
                {name: 'Dest',  minWidth: 60, enableFiltering: false},
                {name: 'Pzs',  minWidth: 44, enableFiltering: false},
                {name: 'Peso',  minWidth: 80, enableFiltering: false},
                {name: 'POD',  minWidth: 65, enableFiltering: false},
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
                {name:'WrhId', displayName: 'Wrhs ID' , minWidth: 80,
                cellTemplate:'<a class="ghost" style="width:100%;height:100%" ng-click= grid.appScope.Open(row.entity.WrhId)>'+
                                '{{row.entity.WrhId}}'+
                                '</a>'},
                {name:'OCompra',displayName: 'Orden de Compra' , minWidth: 160},
                {name:'Accion', displayName: '' , minWidth: 70,
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
            if($scope.searchText.value === "Aerea"){filtro = "AIR";}
            if($scope.searchText.value === "Courier"){filtro = "COC";}
            if($scope.searchText.value === "Box"){filtro = "BOX";}
            if($scope.searchText.value === "LCL"){filtro = "LCL";}
            if($scope.searchText.value === "FCL"){filtro = "FCL";}
            
            //Iniciamos a 0 Totales
                $scope.Piezas = 0;
                $scope.Ctf = 0;
                $scope.Peso = 0;
                $scope.PesoV = 0;
                
            $scope.gridOptions.data = [];
            
            WebServicie.GetHDocuments(username,passhash+'').then(function(Documentos){
                var Docu = JSON.parse(Documentos);
                    
                for(i = 0 ; i<Docu.length ; i++)
                {
                    if( ($scope.CM1== true && Docu[i].routingId == filtro) || (filtro == "" && $scope.CM1== true) || (filtro == "" && Docu[i].shipperId == $scope.Filtro2.id) || (Docu[i].routingId == filtro && Docu[i].shipperId == $scope.Filtro2.id) )
                    {
                        //Sumamos los Valores de la Tabla
                        $scope.Piezas = $scope.Piezas + parseInt(Docu[i].totalPieces);
                        $scope.Ctf = $scope.Ctf + parseInt(Docu[i].totalCft);
                        $scope.Peso = $scope.Peso + parseInt(Docu[i].totalWeight);
                        $scope.PesoV = $scope.PesoV + parseInt(Docu[i].totalVolWeight);
                        $scope.Medida = Docu[i].measure;
             
                        //LLenamos la Tabla con sus Valores
                        $scope.gridOptions.data.push({
                            'Documento':Docu[i].documentNo,
                            'Ruta': Docu[i].routingId,
                            'Orig':Docu[i].origenCityCode,
                            'Dest':Docu[i].destinationCityCode,
                            'Pzs':Docu[i].totalPieces,
                            'Peso':Docu[i].totalWeight,
                            'POD': "---",
                            'Tipo': Docu[i].documentType
                        });          
                    }                     
                }
                          
            });
                      
        };
       
        
        
        //Pasamos los datos por el WebServicie
        $scope.gridOptions.data.splice(0,1);       
        WebServicie.GetHDocuments(username,passhash+'').then(function(Documentos){
            
            var Docu = JSON.parse(Documentos);
                    
            for(var i = 0 ; i<Docu.length ; i++)
            {
                LS.push(parseInt(Docu[i].shipperId)); 
                 //Sumamos los Valores de la Tabla
                $scope.Piezas = $scope.Piezas + parseInt(Docu[i].totalPieces);
                $scope.Ctf = $scope.Ctf + parseInt(Docu[i].totalCft);
                $scope.Peso = $scope.Peso + parseInt(Docu[i].totalWeight);
                $scope.PesoV = $scope.PesoV + parseInt(Docu[i].totalVolWeight);
                $scope.Medida = Docu[i].measure;
             
                //LLenamos la Tabla con sus Valores
                $scope.gridOptions.data.push({
                    'Documento':Docu[i].documentNo,
                    'Ruta': Docu[i].routingId,
                    'Orig':Docu[i].origenCityCode,
                    'Dest':Docu[i].destinationCityCode,
                    'Pzs':Docu[i].totalPieces,
                    'Peso':Docu[i].totalWeight,
                    'POD': "---",
                    'Tipo': Docu[i].documentType
                });          
           }           
           
           
           //Depuramos el ArrayList de Id para que no Hayan repetidos
           WebServicie.GetProveedores(LS.filter(onlyUnique)+'').then(function(Lista){
               var NL = JSON.parse(Lista);
               for(var i = 0; i<NL.length ; i++){
                   LNS.push({
                       'Nombre' :NL[i].name,
                       'id' : NL[i].id
                   });
                   
               }
           });
           
           $scope.NombresShip = LNS;
            
        });
        
        function onlyUnique(value, index, self) { 
            return self.indexOf(value) === index;
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
  
        //Se Encarga de Verificar el cambio en CheckBox
        //Muestra Valores de Fechas y Boton de Busqueda
        $scope.Todos = function() {
            $scope.Filtro();
        };
        
});



