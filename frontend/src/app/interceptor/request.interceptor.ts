import { HttpInterceptorFn } from '@angular/common/http';

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  if (req.headers.get("noauth")) {
    return next(req);
  }

  const account = localStorage.getItem("account");
  if (!account)
    location.href = "/signin"

  const accountNumber = account as string;
  const cloneReq = req.clone({
    setHeaders: {
      accountNumber
    }
  });

  return next(cloneReq);
};
