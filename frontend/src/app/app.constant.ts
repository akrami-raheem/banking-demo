import { InjectionToken } from '@angular/core';

export let APP_CONSTANT = new InjectionToken('app.constant');

export const ServiceUrls ={
    USER_REGISTRATION_URL: 'http://localhost:8080/register',
    PERFORM_TRANSACTION_URL: 'http://localhost:8080/transactions/performTransaction',
    FETCH_CURRENT_WEEK_TRANSACTIONS_URL: 'http://localhost:8080/transactions/',
    AUTHENTICATION_URL:'http://localhost:8080/authenticate'

};