import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../service/authentication.service';
import { RegistrationService } from '../service/registration.service';

@Component({
    selector: 'app-register',
    templateUrl: './registeration.component.html'
})
export class RegisterationComponent implements OnInit {
    form: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private registrationService: RegistrationService
    ) { }

    ngOnInit() {
        this.form = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });
    }

    get f() { return this.form.controls; }

    onSubmit() {
        this.submitted = true;
        if (this.form.invalid) {
            return;
        }

        this.loading = true;
        this.registrationService.registerUser(this.form.value)
            .pipe(first())
            .subscribe(
                data => {
                    if(data.transactionStatus){
                        alert("User registered succesfully.");
                        this.router.navigate(['../login'], { relativeTo: this.route });
                    }else{
                        alert(data.error.errorMessage);
                    }
                },
                error => {
                   alert("Failed to register user");
                    this.loading = false;
                },
                () => this.loading = false
                );
    }
}