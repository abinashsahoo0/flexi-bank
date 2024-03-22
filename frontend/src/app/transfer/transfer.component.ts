import { Component, inject } from '@angular/core';
import { AccountService } from '../services/account.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { ModalComponent } from '../modal/modal.component';
import { ToastComponent } from '../toast/toast.component';

@Component({
  selector: 'app-transfer',
  standalone: true,
  imports: [FormsModule, CurrencyPipe, HeaderComponent, SidebarComponent, ModalComponent, ToastComponent],
  templateUrl: './transfer.component.html',
  styleUrl: './transfer.component.css'
})
export class TransferComponent {
  accountService = inject(AccountService);
  totalRecord = Array(10);
  modalVisible = false;
  toastHeading = ""; toastDescription = ""; toastVisible = false;

  onTransfer(form: NgForm) {
    if (form.valid) {
      const balance = form.value.balance;
      const receiever = form.value.receiever;
      this.accountService.transferBalance(balance, receiever).subscribe({
        next: res => {
          this.generateToast("Success", "Amount Transfered");
        },
        error: err => {
          console.log(err);

          const error = err.error;
          this.generateToast(error['title'], error['detail'])
        },
        complete: () => {
          form.reset();
          this.modalVisible = false;
        }
      })
    }
  }

  generateToast(heading: string, description: string) {
    this.toastHeading = heading;
    this.toastDescription = description;
    this.toastVisible = true;

    setTimeout(() => {
      this.toastVisible = false;
    }, 5000);

  }
}
