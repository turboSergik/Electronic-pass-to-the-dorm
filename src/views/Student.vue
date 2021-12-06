<template>
  <div class="container">
    <div class="student" v-if="studentById">
      <div>
          <div id="student-card">
            <p><img id="student-card-image" src="https://sun9-34.userapi.com/impg/JcEQL0hTcxvUZJquxNFejdEnnBzCM85CxoPV3A/XWc-QUBZMmc.jpg?size=1080x1322&quality=96&sign=b2511f0ffda7e45d82bd135217393864&type=album" /></p>  <!-- :src=LastStudent.img -->
            <p>{{ studentById.name }}</p>
            <p>{{ studentById.surname }}</p>
            <p>Telephone By: {{ studentById.tell }}</p>
          </div>
      </div>
    </div>
    <div v-else>Oh no!!! We have no Students</div>
  </div>
</template>

<script>
export default {
  name: "Student",
  components: {},
  data() {
    return {
      student: {
        name: "",
        surname: "",
        tell: ""
      },
    };
  },
  computed: {
    studentById () {
        return this.$store.getters.getStudentById(this.$route.params.id)
    }
  },
};
</script>
<style scoped>
* {
  box-sizing: border-box;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

button[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  cursor: pointer;
  border-radius: 30px;
  margin: 10px;
}

button[type="submit"]:hover {
  background-color: #45a049;
}

input {
  width: 60%;
  margin: 15px;
  border: 0;
  box-shadow: 0 0 15px 4px rgba(0, 0, 0, 0.06);
  padding: 10px;
  border-radius: 30px;
}

textarea {
  width: 75%;
  resize: vertical;
  padding: 15px;
  border-radius: 15px;
  border: 0;
  box-shadow: 0 0 15px 4px rgba(0, 0, 0, 0.06);
  height: 150px;
  margin: 15px;
}

ul {
  list-style: none;
}

#student-card {
  margin: auto;
  max-width: 80%;
  margin-bottom: 5px;
}

#student-card-image {
  border: 3px solid #000;
  padding: 5px;
  max-width: 400px;
  max-height: 400px;
  border-radius: 4px;
} 
</style>
