import axios from 'axios';

export default class Service {

    url = "http://localhost:8081/api/"

    getAll() {
        return axios.get(this.url + "all");
    }

    save(TeacherDTO) {
        return axios.post(this.url + "save", TeacherDTO)
    }

    delete(id) {
        return axios.get(this.url + "delete/" + id)
    }
}