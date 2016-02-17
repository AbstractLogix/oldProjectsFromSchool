/* global CryptoJS , idFac , idDocu, DocuT ,SerFac , NoFac  */

var appFactura = angular.module("Facturas", [
            'ui.grid',
            'ui.grid.edit',
            'ngDialog',
            'ngStorage',
            'ngTouch',
            'angularSoap',
            'ui.grid.selection',
            'mainApp'
]);



appFactura.controller('MiFacturas', function($scope ,ngDialog, WebServicie ,uiGridConstants , $sessionStorage , $window) {
    
    $scope.message = "Hola desde Controlador Mis Facturas";
    
    //Totales
    $scope.TotalV = 0;
    $scope.Saldo = 0;
    
    //encriptamos 
    var username = $sessionStorage.user+'';
    var password = $sessionStorage.pass+'';
    var passhash = CryptoJS.MD5(username+password);
    
    //Encargado de Abrir Pdf del Wrhs
    $scope.digital = function(Wrhsid){
            $window.open('http://miami.cpslogistics.com:8080/IcarusDocs/reports/scanned/0'+Wrhsid+'.pdf');
    };
    
    //Encaradi de Abrir Pdf de la Factura
    $scope.FacturaDigital = function(){
        //Tomamos el Valor de la Fila Seleccionada
        var p = $scope.gridApi.selection.getSelectedRows();
        if( p.length == 1){
            //Si encontro una Factura
             WebServicie.GetDownload($.trim(p[0].Serie), $.trim(p[0].Numero) ,username,passhash+'').then(function(Factura){
                 alert(Factura);
             });
            
        }else{
            //Si no hay Fila Seleccionada Mostramos Mensaje
            alert('Seleccione una Factura');
        }
        
    };
    
    //Metodo Para Exporta rTabla
    $scope.Exportar = function(){
          var NW = "";
            if($scope.TablaFacturas.data.length == 0){
                sweetAlert("", "No hay Datos Para Exportar", "error");
            }
            else{
                    var id = 0;
                    if($scope.value == 'Q'){id = 2;} //Si Seleccionan Quetzales
                    if($scope.value == 'D'){id = 12;} //Si Seleccionan Dolares
                //Llamamos al Web Servicie
                swal({   title: "Generando",   text: "Espere un Momento",   timer: 120000,   showConfirmButton: false });
                WebServicie.GetReportF(id,username,passhash+'').then(function(User){
                    var a = '<a class="ghostb" href="/ProyectoNewAlejandria/reports/excel/'+User+'_movimientosReport.xls"  target="_self">Descargar</a>';
                    sweetAlert({title: '' ,text:a , confirmButtonText: "Cerrar" ,html:true });  
                });
            }
    }
    
    //PopUPFactura
    $scope.OpenF = function (serie,numero) {   
            NoFac = $.trim(numero);  
            SerFac=  $.trim(serie);
            ngDialog.open({
                templateUrl: 'partials/PopUpFactura.html',
                controller: "PopUpF",
                className: 'ngdialog-theme-plain'
                
            });
    };
    
    //PopUpWrhs
    $scope.Open = function (id) {            
            idWrhs = id;
            ngDialog.open({
                templateUrl: 'partials/PopUpWrhs.html',
                controller: "PopUp"
            });
        
    };
       
    //PopUpDocument
    $scope.OpenD = function (id , tipo) {   
            idDocu = id;
            DocuT = tipo;
            ngDialog.open({
                templateUrl: 'partials/PopUpDocument.html',
                controller: "PopUpD",
                className: 'ngdialog-theme-plain'
                
            });
    };
        
    //Tabla1
    var DataFactura =[];
    //Definicion Tabla1
    $scope.TablaFacturas = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            selectedItems: $scope.mySelections,
            
            //Definimos Headears
            columnDefs : [
                {field:'Fecha', minWidth: 80},
                {field:'Serie',  minWidth: 60,
                    cellTemplate:'<a style="width:100%;height:100%" ng-click= grid.appScope.OpenF(row.entity.Serie,row.entity.Numero) class="ghost">'+
                                '{{row.entity.Serie}}'+
                                '</a>'},
                {field:'Numero',  minWidth: 80,
                    cellTemplate:'<a style="width:100%;height:100%" ng-click= grid.appScope.OpenF(row.entity.Serie,row.entity.Numero) class="ghost">'+
                                '{{row.entity.Numero}}'+
                                '</a>'}, 
                {field: 'Diav',  minWidth: 80,
                     displayName: 'Dias Venc' },
                {field: 'Saldo',  minWidth: 72},
                {field: 'Documento',  visible:false}
            ],
            
            //DEfinimos data
            data: DataFactura
        }; 
    $scope.TablaFacturas.multiSelect = false;
    
    //Tabla2
    var DataDE =[];
    //Definicion Tabla1
    $scope.TablaDE = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,        
            
            //Definimos Headears
            columnDefs : [
                {name:'Documento', minWidth: 99},
                {name:'OrdenCompra',  minWidth: 205,
                     displayName: 'Orden de Compra' },
                {name: 'Tipo', visible:false},
                {name:'Accion',  minWidth: 99,displayName: '',
                    cellTemplate:'<a style="width:100%;height:100%" ng-click= "grid.appScope.OpenD(row.entity.Documento, row.entity.Tipo)" class="ghost">'+
                                'Ver'+
                                '</a>'}
            ],
            
            //DEfinimos data
            data: DataDE
        }; 
    $scope.TablaDE.multiSelect = false;
    
    //Tabla2
    var DataWrhs =[];
    //Definicion Tabla1
    $scope.TablaWrhs = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,        
            
            //Definimos Headears
            columnDefs : [
                {name:'IdWrhs', minWidth: 99,displayName: 'Wrhs ID',
                    cellTemplate:'<a style="width:100%;height:100%" class="ghost" ng-click= grid.appScope.Open(row.entity.IdWrhs)>'+
                                '{{row.entity.IdWrhs}}'+
                                '</a>'},
                {name:'OrdenCompra',  minWidth: 205,
                     displayName: 'Orden de Compra' },
                {name:'Accion',  minWidth: 99,displayName: '',
                    cellTemplate:'<a style="width:100%;height:100%" class="ghost" ng-click= grid.appScope.digital(row.entity.IdWrhs)>'+
                                'Digital'+
                                '</a>'}
            ],
            
            //DEfinimos data
            data: DataWrhs
        }; 
    $scope.TablaWrhs.multiSelect = false;
    
    
    //Metodo LLamado al Precionar Buscar
    $scope.Buscar = function(){
       $scope.FillTablaF($scope.value);
    };
    
    //Metodo que LLena la Tabla Dependiendo de RadioButton
    $scope.FillTablaF = function(Val){
        var id = 0;
        if(Val == 'Q'){id = 2;} //Si Seleccionan Quetzales
        if(Val == 'D'){id = 12;} //Si Seleccionan Dolares
        
        //Limpiamos Totales
        $scope.TotalV = 0;
        $scope.Saldo = 0;
        
        //llamamos al webServicie
        WebServicie.GetBMWS(id,username,passhash+'').then(function(Fac){
            var Lista = JSON.parse(Fac);
            $scope.TablaFacturas.data = [];
            
            var control = 0;
            if($scope.CM1 == true){ control = 1; }
            
            for(var i = 0; i< Lista.length ; i++)
            {   
                var F = new Date(Lista[i].fecha);
                var F1 = $scope.F1;
                var F2 = $scope.F2;

                if( (F1< F && F2>F) || control == 0 )
                {
                     //Validamos si es por Fecha
                    $scope.TotalV = $scope.TotalV +  parseInt(Lista[i].diasVencida);
                    $scope.Saldo = $scope.Saldo + parseInt(Lista[i].saldo);
                
                    $scope.TablaFacturas.data.push({
                        'Fecha': F.getDate()+ "/" +(F.getMonth()+1) + "/" +  F.getFullYear(),
                        'Serie': Lista[i].id.serie,
                        'Numero': Lista[i].id.noDocumento,
                        'Diav': parseInt(Lista[i].diasVencida)+'',
                        'Saldo': Lista[i].saldo,
                        'Documento': Lista[i].descripcion.NoDocu
                    }); 
                }
            }
        });  
    };
    
    //Iniciamos en Q
    $scope.value= 'Q';
    
    //Captura Cambios en RadioButtons
    $scope.$watch('value', function(value) {
       $scope.FillTablaF(value);
    });
    
    //Parte Encargada de Sincronisar fila seleccionada para recuperar el valor
    $scope.TablaFacturas.onRegisterApi = function( gridApi ) {
    $scope.gridApi = gridApi;
        
            $scope.toggleRowSelection = function() {
                $scope.gridApi.selection.clearSelectedRows();
                $scope.TablaFacturas.enableRowSelection = !$scope.TablaFacturas.enableRowSelection;
                $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
            };
            
             //Metodo Cuando se Selecciona una Fila
            $scope.selectRow = function(){
                $scope.TablaDE.data = [];
                $scope.TablaWrhs.data = [];
                var p = $scope.gridApi.selection.getSelectedRows();
                var DN;
                var DT;
                var PN;
                
                if( p.length == 1){
                //Recuperamos el Documento
                    WebServicie.GetFacD(p[0].Serie , p[0].Numero ,username,passhash+'').then(function(Factura){
                        
                        var Fac = JSON.parse(Factura);                           
                        if (Fac.length != 0){
                
                            //LLenamos la Tabla de documentos
                                $scope.TablaDE.data.push({
                                    'Documento' : Fac[0].documentNo,
                                    'OrdenCompra' : Fac[0].poNumber,
                                    'Accion': "",
                                    'Tipo': Fac[0].documentType
                                });
                            
                            //LLenamos la Tabla de Wrhs
                            //Aki llenamos la Tabla Warehouse del Documento primero Encontramos el Documento
                            WebServicie.GetDocumentWrhs(Fac[0].documentNo, Fac[0].documentType, username,passhash+'').then(function(Lista){
                                var LISTAJ = JSON.parse(Lista);
                                //Aki LLenamos el Traking
                                for(var i = 0; i<LISTAJ.length ; i++)
                                {
                                    $scope.TablaWrhs.data.push({
                                        'IdWrhs': LISTAJ[i].id,
                                        'OrdenCompra': LISTAJ[i].orderNo,
                                        'Accion': ""
                                    });
                                }
                            });   
                            
                        }
                    });
                                      
                }
                
                
                
            };
            
    };
    
    //Muestra Valores de Fechas y Boton de Busqueda
    $scope.change = function() {
        
        //Preguntamos si es True el chebbox de fecha
        if( $scope.CM1 == true)
        {
            $scope.C = true;
            $scope.TablaDE.data = [];
            $scope.TablaWrhs.data = [];
            $scope.TablaFacturas.data = [];
            var now = new Date();
            $scope.F1 = now;
            $scope.F2 = now;
            
        }else{
            $scope.C = false;
            $scope.TablaDE.data = [];
            $scope.TablaWrhs.data = [];
            $scope.TablaFacturas.data = [];
            $scope.FillTablaF($scope.value);
        }
    };
    
});


