import http from "../http-common"; 

class MaintenanceLogService {
  getAllMaintenanceLogs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/maintenanceLog/maintenanceLogs`, searchDTO);
  }

  get(maintenanceLogId) {
    return this.getRequest(`/maintenanceLog/${maintenanceLogId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/maintenanceLog?field=${matchData}`, null);
  }

  addMaintenanceLog(data) {
    return http.post("/maintenanceLog/addMaintenanceLog", data);
  }

  update(data) {
  	return http.post("/maintenanceLog/updateMaintenanceLog", data);
  }
  
  uploadImage(data,maintenanceLogId) {
  	return http.postForm("/maintenanceLog/uploadImage/"+maintenanceLogId, data);
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

export default new MaintenanceLogService();
