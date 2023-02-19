angular.module('market-front').controller('welcomeController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/analytics/';

       $scope.loadTop5Products = function () {
            $http.get(contextPath + 'api/v1/analytics/getTopFiveProducts')
                .then(function (response) {
                    $scope.Top5Products = response.data;
                });
        }

     $scope.clearTop5 = function () {
            $http({
                url: contextPath + 'api/v1/analytics',
                method: 'DELETE'
            }).then(function (response) {
                $scope.loadTop5Products();
            });
        };

     $scope.loadTop5Products();
});