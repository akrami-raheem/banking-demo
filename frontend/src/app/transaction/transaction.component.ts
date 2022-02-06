import { Component, OnInit } from "@angular/core";

import { Router } from '@angular/router';
import { TransactionsService } from "../service/transaction.service";

interface TansactionType {
  value: string;
  viewValue: string;
}

@Component({
  selector: "app-transaction",
  templateUrl: "./transaction.component.html",
  styleUrls: ["./transaction.component.css"]
})
export class TransactionComponent implements OnInit {
  transactionAmount : number;
  selectedTransactionType : string;
  
  transactionTypes: TansactionType[] = [
    {value: 'D', viewValue: 'Deposit'},
    {value: 'W', viewValue: 'Withdraw'}
  ];

  constructor(private transactionsService: TransactionsService,
    private router: Router) {}

  ngOnInit() {}

  performTransaction(): void {
    this.transactionsService.performTransaction(this.selectedTransactionType, this.transactionAmount).subscribe(data => {
      if(data && !!data.transactionStatus){
        alert("Transaction Completed.");
      }else{
        alert(data.error.errorMessage);
      }
      this.router.navigate([''])
    });
  }
}
