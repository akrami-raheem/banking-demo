import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { APP_CONSTANT } from "../app.constant";

@Injectable({
    providedIn: "root"
})
export class RegistrationService {
    constructor(private httpClient: HttpClient, @Inject(APP_CONSTANT) public appConstant) { }

    registerUser(userDetails: UserDetails) {
        return this.httpClient
            .post<any>(this.appConstant.USER_REGISTRATION_URL, userDetails);
    }
}