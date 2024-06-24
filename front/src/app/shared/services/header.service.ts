export class HeaderService {
    private _showRateLimitMessage = false;
  
    constructor() {}
  
    setRateLimitMessage(show: boolean) {
      this._showRateLimitMessage = show;
    }
  
    get showRateLimitMessage() {
      return this._showRateLimitMessage;
    }
  }
  