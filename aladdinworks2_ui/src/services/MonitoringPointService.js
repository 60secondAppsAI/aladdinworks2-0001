import http from "../http-common"; 

class MonitoringPointService {
  getAllMonitoringPoints(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/monitoringPoint/monitoringPoints`, searchDTO);
  }

  get(monitoringPointId) {
    return this.getRequest(`/monitoringPoint/${monitoringPointId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/monitoringPoint?field=${matchData}`, null);
  }

  addMonitoringPoint(data) {
    return http.post("/monitoringPoint/addMonitoringPoint", data);
  }

  update(data) {
  	return http.post("/monitoringPoint/updateMonitoringPoint", data);
  }
  
  uploadImage(data,monitoringPointId) {
  	return http.postForm("/monitoringPoint/uploadImage/"+monitoringPointId, data);
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

export default new MonitoringPointService();
