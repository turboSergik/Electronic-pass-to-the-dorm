<template>
  <div id="app">
    <v-app id="inspire">
      <v-navigation-drawer
        v-model="drawer"
        app
      >
      <template v-if="authenticated">
      <div class="students" v-if="Students">
        <ul>
          <li @click="openStudent(student.id)" v-for="student in Students" :key="student.id">
            <div  id="student">
              <p>{{ student.name }}</p>
              <p>{{ student.surname }}</p>
              <p>Telephone: {{ student.tell }}</p>
            </div>
          </li>
        </ul>
      </div>
      </template>
      </v-navigation-drawer>

      <v-app-bar app>
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

        <v-toolbar-title>Application</v-toolbar-title>
        <NavBar />
      </v-app-bar>

      <v-main>
        <router-view />
      </v-main>
    </v-app>
    
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { mapState, mapGetters } from "vuex";

export default {
    components: {
      NavBar,
    },
    data: () => ({ drawer: null }),
    computed: {
    ...mapGetters({ authenticated: "isAuthenticated" }),
    ...mapState({
      Students: state => { 
        if (state.auth.posts) {
          let students = [...state.auth.posts];
          return students.reverse()
        } else {
          return state.auth.posts
        }
      }
    })
    },
    methods: {
      openStudent(id) {
        if (id !== this.$route.params.id)
        this.$router.push({name: 'Student', params: { id }});
      },
    }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

ul {
  list-style-type: none;
}

.v-application ul, .v-application ol {
    padding-left: 2px !important;
}

#student {
  margin: auto;
  margin: 5px;
  border: 3px solid #000;
}
</style>
