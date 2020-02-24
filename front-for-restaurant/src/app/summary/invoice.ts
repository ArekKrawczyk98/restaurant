export class Invoice {
  private id: number;
  private date: Date;
  private moneyThatNeedToBePaid: number;
  moneyReceived: number;
  private rest: number;

  constructor(id: number, date: Date, moneyThatNeedToBePaid: number, moneyReceived: number, rest: number) {
    this.id = id;
    this.date = date;
    this.moneyThatNeedToBePaid = moneyThatNeedToBePaid;
    this.moneyReceived = moneyReceived;
    this.rest = rest;
  }
  getMoney(): number {
    return this.moneyReceived;
  }
}
