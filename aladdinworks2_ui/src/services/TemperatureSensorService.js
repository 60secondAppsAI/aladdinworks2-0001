import http from "../http-common"; 

class TemperatureSensorService {
  getAllTemperatureSensors(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/temperatureSensor/temperatureSensors`, searchDTO);
  }

  get(temperatureSensorId) {
    return this.getRequest(`/temperatureSensor/${temperatureSensorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/temperatureSensor?field=${matchData}`, null);
  }

  addTemperatureSensor(data) {
    return http.post("/temperatureSensor/addTemperatureSensor", data);
  }

  update(data) {
  	return http.post("/temperatureSensor/updateTemperatureSensor", data);
  }
  
  uploadImage(data,temperatureSensorId) {
  	return http.postForm("/temperatureSensor/uploadImage/"+temperatureSensorId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new TemperatureSensorService();
