import { Component, inject } from '@angular/core';
import { AccountService } from '../services/account.service';
import { FormsModule, NgForm } from '@angular/forms';
import { ToastComponent } from '../toast/toast.component';
import { ModalComponent } from '../modal/modal.component';
import { CurrencyPipe } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-withdrawal',
  standalone: true,
  imports: [FormsModule, CurrencyPipe, SidebarComponent, HeaderComponent, ModalComponent, ToastComponent],
  templateUrl: './withdrawal.component.html',
  styleUrl: './withdrawal.component.css'
})
export class WithdrawalComponent {
  accountService = inject(AccountService);
  totalRecord = Array(10);
  modalVisible = false;
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  onWithdrawal(form: NgForm) {
    if (form.valid) {
      const balance = form.value.balance;
      this.accountService.withdrawalBalance(balance).subscribe({
        next: res => {
          this.generateToast("Success", "Amount withdrawaled");
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
