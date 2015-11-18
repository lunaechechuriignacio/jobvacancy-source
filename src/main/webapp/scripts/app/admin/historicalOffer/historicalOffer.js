'use strict';

angular.module('jobvacancyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('historicalOffer', {
                parent: 'admin',
                url: '/historicalOffer',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Historical Posted Offers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/historicalOffer/historicalOffer.html',
                        controller: 'historicalOfferController'
                    }
                },
                resolve: {
                    
                }
            });
    });