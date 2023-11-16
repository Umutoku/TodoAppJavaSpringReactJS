
export default class Todo {
  constructor(
    id = null,
    name = '',
    description = '',
    completed = false,
    dateCreated = new Date(),
    lastUpdated = new Date()
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.completed = completed;
    this.dateCreated = dateCreated;
    this.lastUpdated = lastUpdated;
  }
}
