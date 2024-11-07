import http from "../http-common"; 

class DataCenterService {
  getAllDataCenters(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/dataCenter/dataCenters`, searchDTO);
  }

  get(dataCenterId) {
    return this.getRequest(`/dataCenter/${dataCenterId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/dataCenter?field=${matchData}`, null);
  }

  addDataCenter(data) {
    return http.post("/dataCenter/addDataCenter", data);
  }

  update(data) {
  	return http.post("/dataCenter/updateDataCenter", data);
  }
  
  uploadImage(data,dataCenterId) {
  	return http.postForm("/dataCenter/uploadImage/"+dataCenterId, data);
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

export default new DataCenterService();
