#include <stdio.h>
#include <stdlib.h>

typedef struct trieNode{
    int isWord;
    struct trieNode* children[26];
}trieNode;

int num5LetterWrapper(trieNode* root){
        return num5Rec(root,0);
}

//returns the number of 5 letter words stored within that trie
int num5Rec(trieNode* root, int k){
    if(root == NULL) return 0;
    if(k==5) return root->isWord;
    
    int res = 0;
    for(int i =0; i < 26;i++){
        res += num5Rec(root->children[i],k+1);
    return res;
    }
}