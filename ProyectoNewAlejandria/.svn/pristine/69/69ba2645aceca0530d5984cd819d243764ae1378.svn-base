/* global CryptoJS */

var appMensajes = angular.module("Mensajes", [
            'ngStorage'
]);

appMensajes.controller('MensajesController', function($scope,WebServicie,$sessionStorage,$filter) {
    
    $scope.Mensajes = "Hola desde Controlador Mensajes";
    var now = new Date();
    $scope.F1 = now;
    $scope.F2 = now;
   
    
    $scope.Limpiar = function(){
        $scope.LI = [];
    };
    
    $scope.Buscar = function(){
            var username = $sessionStorage.user;
            var password = $sessionStorage.pass;
            var passhash = CryptoJS.MD5(username+password);
            var info = [];
    
            WebServicie.GetMessages($scope.F1,$scope.F2,username,passhash+'').then(function(Mensajes){
                var LMensajes = JSON.parse(Mensajes);  
                
                for(var i = 0; i<LMensajes.length ; i++ ){
                    
                    var n = new Date();
                    n = LMensajes[i].initDate;
                    n = n.substring(0,12);
                    info.push({
                        'Fecha': n,
                        'Accion': LMensajes[i].action,
                        'Men': LMensajes[i].message
                    });
                }
                
                $scope.LI = info;
            });  
    };
    
});

