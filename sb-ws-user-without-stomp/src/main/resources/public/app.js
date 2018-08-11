'use strict';

angular.module('helloangular',['ngRoute','ngResource'])
  .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider
      .when('/',{templateUrl:'landing.html',controller:'NavigationController'})
      .when('/login', {templateUrl : 'login.html', controller : 'NavigationController'})
      .when('/logout', {templateUrl : 'loggedout.html', controller : 'LogoutController'})
      .otherwise({
        redirectTo: '/'
      });
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';  
  }])
  .controller('LandingPageController', function LandingPageController() {
	  console.log('lnding');
  })
  .controller('NavigationController', function($rootScope, $scope, $http, $location) {
    
    var self = this;
    var authenticate = function(credentials, callback) {

      var headers = credentials ? {authorization : "Basic "
          + btoa(credentials.username + ":" + credentials.password)
      } : {};

      $http.get('user/getUser', {headers : headers}).then(function(retData) {
        var data = retData.data;
        if (data.name) {
            console.log('sucess:' + data.name);
            $rootScope.userName = data.name;
            $rootScope.authenticated = true;
        } else {
          $rootScope.authenticated = false;
          $location.path("/login");
        }
        callback && callback();
      }).catch(function() {
        $rootScope.authenticated = false;
        callback && callback();
        $location.path("/login");
      });
    };

    $scope.credentials = {};
    $scope.doLogin = function() {
        console.log('doLogin:' + $scope.credentials.username);
        authenticate($scope.credentials, function() {
          if ($rootScope.authenticated) {
            $location.path("/");
            self.error = false;
          } else {
            $location.path("/login");
            self.error = true;
          }
        });
    };
  })
  .controller('LogoutController', function($rootScope, $scope, $http, $location) {  
      //$location.path("/Persons");  
      console.log('logout called');
      $http.post('logout', {}).finally(function() {
        $rootScope.authenticated = false;
        $rootScope.isUser = $rootScope.isAdmin = false;
      });
  })
  ;