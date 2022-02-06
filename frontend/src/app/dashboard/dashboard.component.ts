import { formatDate } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { map } from "rxjs/operators";
import { DashboardResponseDto, TransactionsService, TransactionDto } from "../service/transaction.service";




@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  profileName: string;
  currentTime = null;
  currentDate = null;
  readonly WITHDRAW = 'Withdraw';
  readonly DEPOSIT = 'Deposit';
  readonly WITHDRAW_VALUE = 'W';
  dashboardResponseDto: DashboardResponseDto;
  displayedColumns: string[] = ["transactionNumber", "transactionType", "amount", "transactionTime", "closingBalance"];

  constructor(private transactionsService: TransactionsService) {
    this.dashboardResponseDto = {
      accountNumber: 0,
      accountBalance: 0,
      transactionDetails: []
    }
    setInterval(() => {
      let date = new Date();
      this.currentDate = formatDate(date, 'dd-MM-yyyy', 'en-US');
      this.currentTime = formatDate(date, 'hh:mm:ss a', 'en-US');
    }, 1000);
  }

  ngOnInit() {
    this.profileName = sessionStorage.getItem("username");
    this.transactionsService
      .getCurrentWeekTransactions()
      .subscribe(response => {
        this.handleSuccessfulResponse(response)
      },
        error => {
          alert('Failed to get data ' + error);
        });
  }

  handleSuccessfulResponse(response) {
    this.dashboardResponseDto = response;
    this.dashboardResponseDto.transactionDetails = response.transactionDetails.map(transactionRecord => ({
      ...transactionRecord,
      transactionType: transactionRecord.transactionType === this.WITHDRAW_VALUE ? this.WITHDRAW : this.DEPOSIT
    }));
  }
}
