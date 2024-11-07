import http from "../http-common"; 

class CurrentSensorService {
  getAllCurrentSensors(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/currentSensor/currentSensors`, searchDTO);
  }

  get(currentSensorId) {
    return this.getRequest(`/currentSensor/${currentSensorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/currentSensor?field=${matchData}`, null);
  }

  addCurrentSensor(data) {
    return http.post("/currentSensor/addCurrentSensor", data);
  }

  update(data) {
  	return http.post("/currentSensor/updateCurrentSensor", data);
  }
  
  uploadImage(data,currentSensorId) {
  	return http.postForm("/currentSensor/uploadImage/"+currentSensorId, data);
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

export default new CurrentSensorService();
