import http from "../http-common"; 

class CoolingUnitService {
  getAllCoolingUnits(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/coolingUnit/coolingUnits`, searchDTO);
  }

  get(coolingUnitId) {
    return this.getRequest(`/coolingUnit/${coolingUnitId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/coolingUnit?field=${matchData}`, null);
  }

  addCoolingUnit(data) {
    return http.post("/coolingUnit/addCoolingUnit", data);
  }

  update(data) {
  	return http.post("/coolingUnit/updateCoolingUnit", data);
  }
  
  uploadImage(data,coolingUnitId) {
  	return http.postForm("/coolingUnit/uploadImage/"+coolingUnitId, data);
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

export default new CoolingUnitService();
