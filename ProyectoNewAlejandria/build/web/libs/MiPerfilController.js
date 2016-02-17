/* global CryptoJS */

var appPerfil = angular.module('Perfil', [
                'ui.grid',
                'ui.grid.edit',
                'ui.grid.selection',
                'ngTouch'
]);

appPerfil.controller('PerfilController', ['$scope', function ($scope) {

    console.log($sessionStorage.cliente);
    
}]);

appPerfil.controller('TP', function($scope,WebServicie ,$sessionStorage) {


        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
    
   WebServicie.GetWebUser(username,passhash+'').then(function(User){
            var IUser = JSON.parse(User);
            $scope.Nombre = IUser.name;
            $scope.Apellido = IUser.lastname;
            $scope.Codigo = IUser.code;            
            $scope.Direccion = IUser.customerDestinations[0].address;
            $scope.Zip = IUser.customerDestinations[0].zip;
            $scope.Telefono = IUser.customerDestinations[0].tel;
            $scope.Celular = IUser.customerDestinations[0].cel;
            $scope.Fax = IUser.customerDestinations[0].fax;
            $scope.Email = IUser.customerDestinations[0].email;
            $scope.NombreF = IUser.customerDestinations[0].billingName;
            $scope.DireccionF = IUser.customerDestinations[0].billingAddress;
            $scope.Nit = IUser.customerDestinations[0].billingNo;
            $scope.NC = IUser.customerDestinations[0].notifyByMail;
            
            var RVE;
            var ASE;
            
              //Obtenemos Representante de Ventas
              WebServicie.GetRCostumer(IUser.customerDestinations[0].salesPersonId,username,passhash+'').then(function(RPV){
                    $scope.RV = RPV.name + ' '+ RPV.lastname;
                    RVE = RPV.email;
              });
              
              //Obtenemos Agente de Servicios
              WebServicie.GetRCostumer(IUser.customerDestinations[0].customerAgentId,username,passhash+'').then(function(RAS){
                    $scope.AS = RAS.name + ' '+ RAS.lastname;
                    ASE = RAS.email;
              });
              
              
              
    });
    
    
});

appPerfil.controller('TC', function($scope) {

      $scope.com1 = [{'value': 'Español'},{'value': 'Ingles'}];
      $scope.com2 = [{'value': 'Mi Carga'},{'value': 'Mis Embarques'},{'value': 'Mis Facturas'},{'value': 'Historial'}];
      
});

appPerfil.controller('TCCon', function($scope) {

      
});


appPerfil.controller('TCon', function($scope , WebServicie, $sessionStorage , uiGridConstants) {

 //Info Tabla Carga
        $scope.InfoUser =[];
        var myData = [];
        $scope.gridOptions = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            //Definimos Headears
            columnDefs : [
                {field:'Name' , Width:70},
                {field:'LastName', Width: 70},
                {field:'Tel',  Width: 50},
                {field:'Cell',  Width: 50}, 
                {field: 'Cont' , visible: false}
                
            ],
            
            //DEfinimos data
            data: myData
        };  
        $scope.gridOptions.multiSelect = false;
        
        var username = $sessionStorage.user;
        var password = $sessionStorage.pass;
        var passhash = CryptoJS.MD5(username+password);
        
        WebServicie.GetWebUser(username,passhash+'').then(function(User){
              var IUser = JSON.parse(User);
              $scope.InfoUser = IUser;
              
              for(var i = 0 ; i < IUser.customerDestinations[0].customerContacts.length ; i++){
                   var X =  IUser.customerDestinations[0].customerContacts[i];
                   $scope.gridOptions.data.push({
                            'Name':X.name,
                            'LastName': X.lastname,
                            'Tel':X.tel,
                            'Cell':X.cel,
                            'Cont':i
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
        
        
        //Metodo Cuando se Selecciona una Fila
            $scope.selectRow = function(){
                var p = $scope.gridApi.selection.getSelectedRows();
                
                if(p.length > 0){
                    var X =  $scope.InfoUser.customerDestinations[0].customerContacts[p[0].Cont];
                    
                    $scope.Nombres = X.name;
                    $scope.Apellidos = X.lastname;
                    $scope.Puesto = X.jobTitle;
                    $scope.Direccion = X.address;
                    $scope.Cel = X.cel;
                    $scope.Tel = X.tel;
                    $scope.Fax = X.fax;
                    $scope.Email = X.email;
                }

            };
            
        };
  
      
});