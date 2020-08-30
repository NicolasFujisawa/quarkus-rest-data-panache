var app = angular.module("PersonManagement", []);

      //Controller Part
      app.controller("PersonManagementController", function ($scope, $http) {

        //Initialize page with default data which is blank
        $scope.persons = [];

        $scope.form = {
          id: -1,
          name: "",
          birth: "",
          status: ""
        };

        //Now load the data from server
        _refreshPageData();

        //HTTP POST/PUT methods for add/edit person
        $scope.update = function () {
          var method = "";
          var url = "";
          var data = {};
          if ($scope.form.id == -1) {
            //Id is absent so add person - POST operation
            method = "POST";
            url = '/people';
            data.name = $scope.form.name;
            data.birth = $scope.form.birth;
            data.status = $scope.form.status;
          } else {
            //If Id is present, it's edit operation - PUT operation
            method = "PUT";
            url = '/people/' + $scope.form.id;
            data.name = $scope.form.name;
            data.birth = $scope.form.birth;
            data.status = $scope.form.status;
          }

          $http({
            method: method,
            url: url,
            data: angular.toJson(data),
            headers: {
              'Content-Type': 'application/json'
            }
          }).then(_success, _error);
        };

        //HTTP DELETE- delete person by id
        $scope.remove = function (person) {
          $http({
            method: 'DELETE',
            url: '/people/' + person.id
          }).then(_success, _error);
        };

        //In case of edit persons, populate form with person data
        $scope.edit = function (person) {
          $scope.form.name = person.name;
          $scope.form.id = person.id;
          $scope.form.birth = new Date(person.birth);
          $scope.form.status = person.status;
        };

          /* Private Methods */
        //HTTP GET- get all persons collection
        function _refreshPageData() {
          $http({
            method: 'GET',
            url: '/people'
          }).then(function successCallback(response) {
            $scope.persons = response.data;
          }, function errorCallback(response) {
            console.log(response.statusText);
          });
        }

        function _success(response) {
          _refreshPageData();
          _clearForm()
        }

        function _error(response) {
          alert(response.data.message || response.statusText);
        }

        //Clear the form
        function _clearForm() {
          $scope.form.id = -1;
          $scope.form.name = "";
          $scope.form.birth = "";
          $scope.form.status = "";
        }
      });