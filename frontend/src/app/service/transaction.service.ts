import { Inject, Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { APP_CONSTANT } from "../app.constant";

export class TransactionDto {
    trnasactionNumber: string;
    type: string;
    amount: string;
    createdDate: string;
    closingBalance: number;
}

export class TransactionResponseDto {
  transactionStatus: boolean;
  error: Error;
}

export class Error{
  errorMessage: string;
}

export class DashboardResponseDto{
  accountBalance: number = 0;
  accountNumber: number = 0;
  transactionDetails: TransactionDto[] = [];
}

@Injectable({
  providedIn: "root"
})
export class TransactionsService {
  constructor(private httpClient: HttpClient,
    @Inject(APP_CONSTANT) public appConstant) {}

  getCurrentWeekTransactions() {
    return this.httpClient.get<DashboardResponseDto>(this.appConstant.FETCH_CURRENT_WEEK_TRANSACTIONS_URL +sessionStorage.getItem("username"));
  }

  public performTransaction(selectedTransactionType, transactionAmount) {
    return this.httpClient.post<TransactionResponseDto>(
      this.appConstant.PERFORM_TRANSACTION_URL,
      {
        userName: sessionStorage.getItem("username"),
        amount: transactionAmount,
        transactionType: selectedTransactionType
      }
    );
  }
}
