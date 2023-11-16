export class Page {
    constructor(size = 0, totalElements = 0, totalPages = 0, number = 0) {
      this.size = size;
      this.totalElements = totalElements;
      this.totalPages = totalPages;
      this.number = number;
    }
  }
  
  export class GeneratePage {
    constructor(displayValue = 0, value = 0) {
      this.displayValue = displayValue;
      this.value = value;
    }
  }
  