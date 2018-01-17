angular.module('test', [])
    .controller('Shopping', function($scope, $http) { //get initial data of the store.

        $http.get('http://localhost:8080/available').
        then(function (response) {
            $scope.items = response.data;
        });

        $scope.showSummary = function() { // responsible for retrieving purchase data of the selected user
            $http.get('http://localhost:8080/summary?user=' + $scope.selectedItem).then(function (response) {
                $scope.summar = response.data;
            });
        };

        $scope.buyItems = function() {  //used to react to "buy" button to submit purchase request. it it also resposinble for refreshing the page after buying.
            $scope.summar[0].count = $scope.selectedItem;

            $http.post('http://localhost:8080/buy', $scope.summar).then(function (response) {
//                $scope.items = response.data;
                $scope.postItems = response.data;
                angular.forEach($scope.postItems, function(data,index){
                    $scope.items[index].count = data.count;
                    $scope.summar[index].count = data.tmpCount;
                    $scope.summar[index].tmpCount = 0;
                });
            });
        };

        $scope.update = function() {  //used to response to user selection changing and it will activiate restful api calling for purchasing data.
            $scope.currentItem = $scope.selectedItem;
            $scope.showSummary();
        }
    });

