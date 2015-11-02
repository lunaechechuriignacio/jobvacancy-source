'use strict';

angular.module('jobvacancyApp')
    .controller('historicalOfferController', function ($scope, historicalOfferService) {
        $scope.offers = historicalOfferService.get();
    });