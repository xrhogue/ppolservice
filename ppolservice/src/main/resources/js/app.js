(function() {
	var app = angular.module('game', ['ngGrid', 'ngResource', 'ui.bootstrap']);
	app.factory('StatService', ['$http', '$log', '$resource', function($http, $log, $resource) {						
		return $resource("http://localhost:8080/stat/:verb", {verb: 'list'}, {
			getStats: {method: 'GET', isArray: true}
		});
	}]);
	
	app.controller('GameController', function() {
	});
	
	app.controller('PanelController', function() {
		this.tab = 1;
		
		this.selectTab = function(setTab) {
			this.tab = setTab;
		};
		
		this.isSelected = function(checkTab) {
			return this.tab === checkTab;
		};
	});
	
	app.controller('StatsController', ['$scope', '$modal', '$log', '$window', 'StatService', function($scope, $modal, $log, $window, StatService) {
		var cellTemplate='<div class="ngCellText" data-ng-model="row"><a data-ng-click="updateSelectedRow(row,$event)"><img alt="Edit" src="edit.png" height="16px" width="16px"/></a><a data-ng-click="deleteSelectedRow(row,$event)"><img alt="Delete" src="delete.png" height="16px" width="16px"/></a></div>';
		$scope.stats = StatService.query();
		
//		$scope.$on('ngGridEventEndCellEdit', function(evt) {
//            statService.saveStat(evt.targetScope.row.entity);
//         });
		
		$scope.gridOptions = {
				data : 'stats',
				columnDefs: [{field: 'id', displayName: 'Id', cellClass: 'gridCellNumberStyle'},
				             {field: 'code', displayName: 'Code'},
				             {field: 'shortForm', displayName: 'Short Name'},
				             {field: 'longForm', displayName: 'Long Name'},
				             {field: 'multiplier', displayName: 'Multiplier', cellClass: 'gridCellNumberStyle'},
				             {field: '', cellTemplate: cellTemplate, width: '48px'}]
		};
		
		$scope.addNewAttribute = function() {
			var entity = new StatService();
			
			$scope.stats.push(entity);
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'statDlg.html',
		    	controller: ModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return entity;
		    		},
		    		stats: function () {
		    			return $scope.stats;
		    		}
		    	}
		    });			
		};
		
		$scope.updateSelectedRow = function(row) {
			$scope.myrow = row.entity;
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'statDlg.html',
		    	controller: ModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return row.entity;
		    		},
		    		stats: function () {
		    			return $scope.stats;
		    		}
		    	}
		    });
		};
	    
		$scope.deleteSelectedRow = function(row) {
			$scope.selectedItem = row.entity;
			
			var deleteItem = $window.confirm('Delete ' + $scope.selectedItem.shortForm + '?');
			
			if (deleteItem) {				
				$scope.stats.splice(row.rowIndex, 1);
				$scope.selectedItem.$delete({verb: 'delete', id: $scope.selectedItem.id});
			}
		};
	}]);
	
	app.directive('uppercase', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var uppercase = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = '';
		           
		           var uppercased = inputValue.toUpperCase();
		           
		           if (attrs.ngMaxlength != undefined && uppercased.length > attrs.ngMaxlength) {
		        	   uppercased = uppercased.substr(0, attrs.ngMaxlength);
		           }
		           
		           if (uppercased !== inputValue) {
		        	   modelCtrl.$setViewValue(uppercased);
		        	   modelCtrl.$render();
		           }
		           
		           return uppercased;
		    	}
		    	
		        modelCtrl.$parsers.push(uppercase);
		         
		        uppercase($parse(attrs.ngModel)(scope));  // capitalize initial value
		    }
		};
	}]);
	
	app.directive('capitalize', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var capitalize = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = '';
		           		           
		           var capitalized = inputValue;
		           
		           if (inputValue.length > 0) {
		        	   capitalized = inputValue.substr(0, 1).toUpperCase();
		        	   
		        	   if (inputValue.length > 1) {
		        		   capitalized += inputValue.substr(1, inputValue.length - 1).toLowerCase();
		        	   }
		           }
		           
		           if (attrs.ngMaxlength != undefined && uppercased.length > attrs.ngMaxlength) {
		        	   capitalized = capitalized.substr(0, attrs.ngMaxlength);
		           }
		           
		           if (capitalized !== inputValue) {
		        	   modelCtrl.$setViewValue(capitalized);
		        	   modelCtrl.$render();
		           }
		           
		           return capitalized;
		    	}
		    	
		        modelCtrl.$parsers.push(capitalize);
		         
		        capitalize($parse(attrs.ngModel)(scope));  // capitalize initial value
		    }
		};
	}]);
	
	app.directive('checkRange', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var getValue = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = attrs.ngMin;
		           
		           value = inputValue;
		           
		           if (attrs.ngMin != undefined && value < attrs.ngMin) {
		        	   value = attrs.ngMin;
		           }
		           
		           if (attrs.ngMax != undefined && value > attrs.ngMax) {
		        	   value = attrs.ngMax;
		           }
		           
		           if (value !== inputValue) {
		        	   modelCtrl.$setViewValue(value);
		        	   modelCtrl.$render();
		           }
		           
		           return value;
		    	}
		    	
		        modelCtrl.$parsers.push(getValue);
		         
		        getValue($parse(attrs.ngModel)(scope));  // force value into range
		    }
		};
	}]);
	
	var ModalInstanceCtrl = function ($scope, $modalInstance, item, stats) {
		$scope.availableCodes = getAvailableCodes(stats);
		$scope.item = item;
		  
		var id = item.id;
		var code = item.code;
		var shortForm = item.shortForm;
		var longForm = item.longForm;
		var multiplier = item.multiplier;

		$scope.ok = function () {
			item.$save({verb: 'update', stat: item})
			  
			$modalInstance.close(item);
		};

		$scope.cancel = function () {
			item.id = id;
			item.code = code;
			item.shortForm = shortForm;
			item.longForm = longForm;
			item.multiplier = multiplier;
			
			if (item.id == undefined) {
				stats.pop();
			}
			
			$modalInstance.dismiss('cancel');
		};
		
		function getAvailableCodes(stats) {
			var availableCodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
			
			stats.forEach(function(stat) {
				if (availableCodes.indexOf(stat.code) > -1 && item.code != stat.code) {
					availableCodes.splice(availableCodes.indexOf(stat.code), 1);
				}
			});
			
			return availableCodes;
		}
	};
})();
