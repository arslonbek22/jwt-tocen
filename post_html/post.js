// Fetch posts data from an API (replace with your API endpoint)


window.onload = function () {
    fetch('http://localhost:8080/api/posts', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            const postsContainer = document.getElementById('posts-container');

            data.forEach(post => {
                const postElement = document.createElement('div');
                postElement.classList.add('col');
                postElement.innerHTML = `
        <div class="card">
          <div class="card-header">
            <h3>${post.title}</h3>
          </div>
          <div class="card-body">
            <p class="card-text">${post.content}</p>
            <p class="card-text"><b>By:</b> ${post.user}</p>

            <h4>Comments</h4>
            <ul id="comments-${post.id}" class="list-group">
              </ul>
          </div>
        </div>
      `;

                postsContainer.appendChild(postElement);
            });
        })
        .catch(error => console.error('Error fetching posts:', error));
}
// Function to fetch and display comments (replace with actual API calls)
function fetchComments(postId) {
    fetch(`http://localhost:8080/api/posts/${postId}/comments`)
        .then(response => response.json())
        .then(comments => {
            const commentsList = document.getElementById(`comments-${postId}`);

            commentsList.innerHTML = ''; // Clear existing comments

            comments.forEach(comment => {
                const commentItem = document.createElement('li');
                commentItem.classList.add('list-group-item');
                commentItem.textContent = `${comment.name}: ${comment.body}`;

                commentsList.appendChild(commentItem);
            });
        })
        .catch(error => console.error('Error fetching comments:', error));
}

// Add event listener to load comments on click (replace with your logic)
const postElements = document.querySelectorAll('.card');
postElements.forEach(postElement => {
    postElement.addEventListener('click', () => {
        const postId = postElement.querySelector('.card-header h3').textContent;
        fetchComments(postId); // Fetch comments for the clicked post
    });
});