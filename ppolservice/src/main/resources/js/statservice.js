(function(){
	var app = angular.module('game');
	
	app.factory('StatService', function(){
		var stats = [{
			code: 'X',
			shortForm: 'XXX',
			longForm: 'Xxxxxx',
			multiplier: 3,
			editable: true
		},
		{
			code: 'Y',
			shortForm: 'YYY',
			longForm: 'Yyyyyyy',
			multiplier: 2,
			editable: false
		}];
		
		function getStats() {
			return stats;
		}
		
		function saveStat(stat) {
			console.log("Saved");
		}
		
		return {
			getStats: getStats;
		}
	});
})();