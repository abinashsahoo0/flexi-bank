import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Datatable } from '../model/datatable';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  BASE_URL = "http://localhost:1205/banking/api/v1/transactions"
  http = inject(HttpClient);

  constructor() { }

  getCreditedAmount(pageNumber: number, pageSize: number) {
    return this.http.get<Datatable>(
      this.BASE_URL + "/credit?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
  }
  
  getDebitedAmount(pageNumber: number, pageSize: number) {
    return this.http.get<Datatable>(
      this.BASE_URL + "/debit?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
  }
  
  getTransferredAmount(pageNumber: number, pageSize: number) {
    return this.http.get<Datatable>(
      this.BASE_URL + "/transfer?pageNumber=" + pageNumber + "&pageSize=" + pageSize);
  }
}
