'use strict';

var app = angular.module("myApp", ['ngTable', 'ngResource']);

app.factory('Company', function ($resource) {
    return $resource('api/company/:id', {}, {
        'query': { method: 'GET', isArray: true},
        'get': {
            method: 'GET'
        },
        'update': { method:'PUT' }
    });
});
app.factory('CompanyOwner', function ($resource) {
    return $resource('api/companyOwner/:id', {}, {
        'update': { method:'POST' }
    });
});


app.controller('CompanyController', function ($scope, ngTableParams, Company, CompanyOwner) {
    	    	
    	$scope.cssLeft = 'col-sm-3';
    	$scope.cssRight = 'col-sm-9';    	
    	
    	$scope.clearViews = function () {
        	$scope.editMode = false;
        	$scope.singleMode = false;
        	$scope.singleOwnerMode = false;
    	};
    	$scope.clearViews();
    	
    	$scope.companies = []; 
    	$scope.companyTmp = {};
    	
        $scope.tableParams = new ngTableParams({
        	sorting: { name: "asc" } 
        }, {
	        getData: function($defer, params) {	        	
                Company.query({
                    },	        		
	        		function(result, headers) {
		                $scope.companies = result;
	            });
	        }
        });   
        
        //Company
        $scope.companyCreate = function () {
        	$scope.clearViews();
        	$scope.singleMode = true;
        	$scope.editMode = true;
        	$scope.companyTmp = {};
        };
        
        $scope.companyCreateSave = function () {
        	Company.update($scope.companyTmp,
            	function () {
        			$scope.tableParams.reload();
        			$scope.clearViews();
        		});
        };
        
        $scope.companyDetail = function (id) {
        	$scope.clearViews();
        	$scope.singleMode = true;
        	Company.get({id: id}, function(result) {
            	$scope.companyTmp = result;
            });
        };
        
        $scope.companyEdit = function (id) {
        	$scope.companyDetail(id);
        	$scope.editMode = true;
        };
        
        $scope.companyHide = function () {
        	$scope.clearViews();
        }
        
        //Owner
        $scope.companyOwnerAdd = function (result) {
        	$scope.clearViews();
        	$scope.singleOwnerMode = true;
        	$scope.companyOwnerTmp = {};
        	$scope.companyTmp = result;
        }
        
        $scope.companyOwnerAddSave = function () {
        	//set actual edited company
        	$scope.companyOwnerTmp.companyId = $scope.companyTmp.id;
        	CompanyOwner.update($scope.companyOwnerTmp,
            	function () {
        			$scope.tableParams.reload();
        			$scope.clearViews();
        		});
        }
        
       
    });

