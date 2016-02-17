/* global WebService, CryptoJS, angular, sampleApp */


// create angular app
var app = angular.module('myApp', ['angularSoap', 'ngStorage']);



app.factory('testService', ['$soap', function ($soap) {

    //Area de Variables Globales
    var base_url = "http://localhost:8080/ProyectoNewAlejandria/UsersWSService";
    return {

        //Metodo que Invoca WebServicie Prueba
        validateUser: function (username, authenticator) {
            return $soap.post(base_url, "validateUser", {
                username: username,
                authenticator: authenticator
            });
        }

    };

}]);

// create angular controller
app.controller('mainController', function ($scope, $sessionStorage, testService) {

    // function to submit the form after all validation has occurred			
    $scope.submitForm = function () {

        // check to make sure the form is completely valid
        if ($scope.userForm.$valid) {

            //Guardamos Variables de Session
            $sessionStorage.username = $scope.username;
            $sessionStorage.password = $scope.password;

            console.log($sessionStorage.username);
            console.log($sessionStorage.password);

            //Convertimos el Password
            var passhash = CryptoJS.MD5($scope.username + $scope.password);
            $scope.passhash = passhash + '';

            //Pasamos los datos por el WebServicie
            testService.validateUser($scope.username + '', passhash + '').then(function (response) {
                $scope.resp = response;
                if (response.toString() === '1') {
                    alert('Login Correcto ---->');
                } else {
                    alert('Login Incorrecto ---->');
                }
            });


        }

    };

});