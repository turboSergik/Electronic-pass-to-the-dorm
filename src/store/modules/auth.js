import axios from "axios";

const state = {
  user: null,
  posts: null,
};

const getters = {
  isAuthenticated: (state) => !!state.user,
  StateStudents: (state) =>{ let test = [...state.posts]; return test.reverse(); },
  StateLastStudent: (state) => state.posts[state.posts.length - 1],
  StateUser: (state) => state.user,
  getStudentById: (state) => id => {
    return state.posts.find(student => student.id === +id);
  }
};

const actions = {
  async Register({dispatch}, form) {
    await axios.post('register', form)
    let UserForm = new FormData()
    UserForm.append('username', form.username)
    UserForm.append('password', form.password)
    await dispatch('LogIn', UserForm)
  },

  async LogIn({commit}, user) {
    //await axios.post("login", user);
    await commit("setUser", user.get("username"));
  },

  async CreatePost({ dispatch }, post) {
    await axios.post("post", post);
    return await dispatch("GetPosts");
  },

  async GetPosts({ commit }) {
    let response = [{ id: 0, name: "Vlad", surname: "Chichuk", tell: "+37533413123" }, { id: 1, name: "Serega", surname: "Pisyn", tell: "+3753325678" }]  // await axios.get("students");
    commit("setStudents", response);
  },

  async LogOut({ commit }) {
    let user = null, students = null;
    commit("logout", { user, students });
  },
};

const mutations = {
  setUser(state, username) {
    state.user = username;
    console.log('hi');
  },

  setStudents(state, students) {
    state.posts = students;
  },
  logout(state, data) {
    state.user = data.user;
    state.posts = data.students;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
