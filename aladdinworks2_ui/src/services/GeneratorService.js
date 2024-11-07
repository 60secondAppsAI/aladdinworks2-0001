import http from "../http-common"; 

class GeneratorService {
  getAllGenerators(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/generator/generators`, searchDTO);
  }

  get(generatorId) {
    return this.getRequest(`/generator/${generatorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/generator?field=${matchData}`, null);
  }

  addGenerator(data) {
    return http.post("/generator/addGenerator", data);
  }

  update(data) {
  	return http.post("/generator/updateGenerator", data);
  }
  
  uploadImage(data,generatorId) {
  	return http.postForm("/generator/uploadImage/"+generatorId, data);
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

export default new GeneratorService();
