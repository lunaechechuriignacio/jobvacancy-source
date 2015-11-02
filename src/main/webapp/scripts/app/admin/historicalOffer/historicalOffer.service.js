'use strict';

angular.module('jobvacancyApp')
    .factory('historicalOfferService', function ($resource) {
        return $resource('api/offers', {}, {
            'get': { method: 'GET', isArray: true}
        });
    });