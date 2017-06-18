(function(angular) {
  angular.module("iBoard", []);
}(angular));

(function(angular) {
  var AppController = function($scope, $http) {
    $scope.trained = false;
    $scope.suggestions = [];

    $scope.$watch('fragment', function (newValue, oldValue, scope) {
        if (newValue !== oldValue) {
            suggest(newValue);
        }
    });

    $scope.train = function() {
     $http({
        method: 'POST',
        url: '/train',
        data: $scope.passage
     }).then(function successCallback(response) {
        $scope.trained = true;
        $scope.suggestions = [];
        $scope.fragment = '';
     });
    };


    function suggest(fragment) {
    $scope.suggestions = [];
     $http({
        method: 'GET',
        url: '/suggest/'+fragment,
        data: $scope.passage
     }).then(function successCallback(response) {
        $scope.suggestions = response.data;
     });
    };
}

  AppController.$inject = ['$scope', '$http'];
  angular.module("iBoard").controller("AppController", AppController);
}(angular));