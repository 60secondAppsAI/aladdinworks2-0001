import http from "../http-common"; 

class AlertService {
  getAllAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/alert/alerts`, searchDTO);
  }

  get(alertId) {
    return this.getRequest(`/alert/${alertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/alert?field=${matchData}`, null);
  }

  addAlert(data) {
    return http.post("/alert/addAlert", data);
  }

  update(data) {
  	return http.post("/alert/updateAlert", data);
  }
  
  uploadImage(data,alertId) {
  	return http.postForm("/alert/uploadImage/"+alertId, data);
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

export default new AlertService();
