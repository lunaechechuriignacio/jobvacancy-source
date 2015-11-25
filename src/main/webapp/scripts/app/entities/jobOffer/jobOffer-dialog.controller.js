'use strict';

var a=angular.module('jobvacancyApp')

a.controller('JobOfferDialogController',
	['$scope', '$stateParams', '$modalInstance', 'entity', 'JobOffer', 'User', 'republish','satisfied',
		function ($scope, $stateParams, $modalInstance, entity, JobOffer, User, republish,satisfied) {

  			$scope.today =new Date().toISOString();
			$scope.jobOffer = entity;
		
									
			$scope.users = User.query();
			$scope.load = function (id) {
				JobOffer.get({
					id : id
				}, function (result) {
					$scope.jobOffer = result;
					for (var o in jobOffer){

						
					}
				});
			};

			
 			var onSaveFinished = function (result) {
				$scope.$emit('jobvacancyApp:jobOfferUpdate', result);
				$modalInstance.close(result);
			};

			$scope.save = function () {

				if ($scope.jobOffer.id != null) {
					
				
            if (republish == true) {
							$scope.jobOffer.id=null;
						  JobOffer.save($scope.jobOffer, onSaveFinished);
				
					  }else{
				        	if (satisfied == true) {
					      		$scope.jobOffer.satisfied=true;
					      	 
						   JobOffer.update($scope.jobOffer, onSaveFinished);
				
				   	}else{
						 
						JobOffer.update($scope.jobOffer, onSaveFinished);
					         }
           }
				} 
				else {
					 
					JobOffer.save($scope.jobOffer, onSaveFinished);
				
				}

			};

			$scope.clear = function () {
				$modalInstance.dismiss('cancel');
			};
		}
	]);


