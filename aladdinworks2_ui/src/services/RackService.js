import http from "../http-common"; 

class RackService {
  getAllRacks(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/rack/racks`, searchDTO);
  }

  get(rackId) {
    return this.getRequest(`/rack/${rackId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/rack?field=${matchData}`, null);
  }

  addRack(data) {
    return http.post("/rack/addRack", data);
  }

  update(data) {
  	return http.post("/rack/updateRack", data);
  }
  
  uploadImage(data,rackId) {
  	return http.postForm("/rack/uploadImage/"+rackId, data);
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

export default new RackService();
