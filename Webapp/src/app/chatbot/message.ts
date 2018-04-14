export class Message {
  from: {
    id: string,
    name: string
  };
  type: any;
  text: string;
  token: string;

  constructor(){
    this.from = {
      id: '',
      name: ''
    };
    this.from.id = '';
    this.from.name = '';
    this.type = 'message';
    this.text = '';
    this.token = '';
  }

}
