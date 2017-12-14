package br.com.hellmets.motocerol;

import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;
import br.com.hellmets.motocerol.AndGraph.AGTimer;

/**
 * Created by breno on 16/11/2017.
 */

public class CenaJogo extends AGScene
{
    //Instância das variáveis (SPRITES)
    AGSprite hero = null;
    AGSprite enemy = null;
    AGSprite icone_personagem = null;
    AGSprite [] vida_personagem = null;
    AGSprite [] vida_monstro = null;
    AGSprite colisor = null;
    AGSprite colisor2 = null;
    AGSprite back = null;

    //Instância das variáveis (TIME)
    AGTimer atualizaAtaque = null;
    AGTimer temporizador = null;
    AGTimer ataqueBoss = null;
    AGTimer auxFlag = null;

    //Flags de controle
    int indice = 0;
    int i = 0;
    int flag = 0;

    //Construtor da Classe
    public CenaJogo(AGGameManager manager)
    {
        super(manager);
    }

    @Override
    public void init()
    {
        //Inicialização das variáveis
        atualizaAtaque = new AGTimer();
        temporizador = new AGTimer();
        ataqueBoss = new AGTimer();
        auxFlag = new AGTimer();

        vida_monstro = new AGSprite[5];
        vida_personagem = new AGSprite[3];

        //Métodos de criação dos Sprites tanto para animação quanto de background
        //Aqui são setados também os parametros de proporção dos Sprites
        back = this.createSprite(R.mipmap.background, 1,1);
        back.setScreenPercent(100,100);

        icone_personagem = this.createSprite(R.mipmap.faceset, 1,1);
        icone_personagem.setScreenPercent(10,15);

        //Sprites de vida do PERSONAGEM
        for (int i = 0; i < 3; i++ )
        {
            vida_personagem[i] = this.createSprite(R.mipmap.defesa_personagem, 1, 1);
            vida_personagem[i].setScreenPercent(5, 10);
        }

        //Sprites de vida do BOSS
        for(int i = 0; i < 5; i++)
        {
            vida_monstro[i] = this.createSprite(R.mipmap.vida_monstro, 1,1);
            vida_monstro[i].setScreenPercent(5,10);
        }

        //Cria uma animação com os sprites definidos
        hero = this.createSprite(R.mipmap.knight, 6,4);
        hero.setScreenPercent(25,30);

        enemy = this.createSprite(R.mipmap.giant, 8,4);
        enemy.setScreenPercent(50,60);

        //Colisor criado para a contagem de dano aplicado nos personagens
        colisor = this.createSprite(R.mipmap.barra, 1,1);
        colisor.setScreenPercent(7,7);

        colisor2 = this.createSprite(R.mipmap.barra, 1,1);
        colisor2.setScreenPercent(7,7);

        //Configura ALTURA e LARGURA de todos os sprites na tela do celular
        back.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        back.vrPosition.setY(AGScreenManager.iScreenHeight/2);

        icone_personagem.vrPosition.setX(AGScreenManager.iScreenHeight/9.5f);
        icone_personagem.vrPosition.setY(AGScreenManager.iScreenWidth/1.8f);

        //** VIDAS PERSONAGEM, apresentados em um vetor na tela
        vida_personagem[0].vrPosition.setX(AGScreenManager.iScreenHeight/2.2f);
        vida_personagem[0].vrPosition.setY(AGScreenManager.iScreenWidth/1.8f);

        vida_personagem[1].vrPosition.setX(AGScreenManager.iScreenHeight/2.8f);
        vida_personagem[1].vrPosition.setY(AGScreenManager.iScreenWidth/1.8f);

        vida_personagem[2].vrPosition.setX(AGScreenManager.iScreenHeight/3.8f);
        vida_personagem[2].vrPosition.setY(AGScreenManager.iScreenWidth/1.8f);

        //** VIDAS BOSS, apresentados em um vetor na tela
        vida_monstro[4].vrPosition.setX(AGScreenManager.iScreenHeight/0.66f);
        vida_monstro[4].vrPosition.setY(AGScreenManager.iScreenWidth/16.7f);

        vida_monstro[3].vrPosition.setX(AGScreenManager.iScreenHeight/0.76f);
        vida_monstro[3].vrPosition.setY(AGScreenManager.iScreenWidth/16.7f);

        vida_monstro[2].vrPosition.setX(AGScreenManager.iScreenHeight/0.90f);
        vida_monstro[2].vrPosition.setY(AGScreenManager.iScreenWidth/16.7f);

        vida_monstro[1].vrPosition.setX(AGScreenManager.iScreenHeight/1.10f);
        vida_monstro[1].vrPosition.setY(AGScreenManager.iScreenWidth/16.7f);

        vida_monstro[0].vrPosition.setX(AGScreenManager.iScreenHeight/1.42f);
        vida_monstro[0].vrPosition.setY(AGScreenManager.iScreenWidth/16.7f);

        hero.vrPosition.setX(AGScreenManager.iScreenHeight/3.0f);
        hero.vrPosition.setY(AGScreenManager.iScreenWidth/4.0f);

        colisor2.vrPosition.setX(AGScreenManager.iScreenHeight/3.0f);
        colisor2.vrPosition.setY(AGScreenManager.iScreenWidth/4.0f);

        colisor.vrPosition.setX(AGScreenManager.iScreenHeight/1.0f);
        colisor.vrPosition.setY(AGScreenManager.iScreenWidth/4.0f);

        enemy.vrPosition.setX(AGScreenManager.iScreenHeight/1.0f);
        enemy.vrPosition.setY(AGScreenManager.iScreenWidth/2.9f);

        //Seta para invisivel o colisor
        colisor.bVisible = false;
        colisor2.bVisible = false;

        //Cria a animação com os sprites selecionados
        //ANIMAÇÃO PERRSONAGEM
        hero.addAnimation(10,true, 0,5);
        hero.addAnimation(10, false, 6,11);
        hero.setCurrentAnimation(0);

        //ANIMAÇÃO BOSS (INIMIGO)
        enemy.addAnimation(10,true,0,6);
        enemy.addAnimation(10,!true,7,13);
        enemy.addAnimation(10,false,29,31);
        enemy.setCurrentAnimation(0);

        //Método de espelhamento dos sprites
        hero.iMirror = AGSprite.HORIZONTAL;
        enemy.iMirror = AGSprite.HORIZONTAL;
    }

    @Override
    public void restart()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public void loop()
    {

        //Atualiza a flag
        ataqueBoss.update();

        //Método de volta para o menu principal do jogo
        //Ao clicar no botão de voltar do celular, volta para a cena de menu
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            //Seleciona a cena de MENU
            this.vrGameManager.setCurrentScene(0);
            return;
        }

        //PERSONAGEM, ANIMAÇÃO DE ATAQUE E DE MOVIMENTO
        //Método de ataque do personagem
        //Ao clicar na tela, é gerado a ação de ataque do persongem e a movimentação
        if(AGInputManager.vrTouchEvents.screenClicked())
        {
            if (hero.getCurrentAnimationIndex() == 0)
            {
                hero.moveTo(250, hero.vrPosition.getX()+95 ,hero.vrPosition.getY() );
                hero.setCurrentAnimation(1);
            }
        }

        //Método de colisão do personagem para contabilizar o dano
        //Quando contabilizado, retorna para a posição inicial
        if(hero.collide(colisor))
        {
            //Adicionam a animação de "DEBILITADO" do Boss
            if(enemy.getCurrentAnimationIndex() == 0)
            {
                enemy.setCurrentAnimation(2);
            }
            else if(enemy.getCurrentAnimationIndex() == 1)
            {
                enemy.setCurrentAnimation(2);
            }

            vida_monstro[indice].bVisible = false;
            indice++;
            hero.moveTo(1000,AGScreenManager.iScreenHeight/3f, AGScreenManager.iScreenWidth/4.0f);
        }

        //Se a vida do Boss chegar no limite do vetor
        //Retorna para a página de menu do jogo
        if(!vida_monstro[4].bVisible)
        {
            this.vrGameManager.setCurrentScene(2);
            indice = 0;
            return;
        }

        //Se a animação atual (que no caso é a de ataque) do personagem chegar ao fim,
        //voltará para a animação inicial pré determinada
        if(hero.getCurrentAnimation().isAnimationEnded())
        {
            hero.setCurrentAnimation(0);
        }

        //INIMIGO, ANIMAÇÃO DO LOOP E RANDOMIZAÇÃO DE ATAQUE
        //Verifica se o inimigo já atacou
        if(ataqueBoss.isTimeEnded())
        {
            double aleatorio = Math.random();

            if (aleatorio > 0.5)
            {
                while(!auxFlag.isTimeEnded() && flag != 2)
                {
                    auxFlag.update();

                    if(auxFlag.isTimeEnded())
                    {
                        flag++;
                    }
                }
                auxFlag.restart();

                if(enemy.getCurrentAnimationIndex() == 0 )
                {
                    enemy.setCurrentAnimation(1);

                }
            }
            else //Senão
            {
                while(!auxFlag.isTimeEnded() && flag != 3)
                {
                    auxFlag.update();

                    if(auxFlag.isTimeEnded())
                    {
                        flag++;
                    }
                }

                //Se o Boss colidir com o personagem
                //As vidas do Personagem desaparece
                //ESTA PARTE AINDA NÃO ESTA FUNCIONANDO COMO DEVERIA
                if (enemy.collide(colisor2))
                {
                    vida_personagem[i].bVisible = false;
                    i++;
                }

                //Se alcançar o limite de vidas do personagem
                //É chamado a cena de "PERDEU"
                if(vida_personagem[0].bVisible == false)
                {
                    this.vrGameManager.setCurrentScene(3);
                    i = 0;
                    return;
                }
                //AQUI FINALIZA

                auxFlag.restart();

                if(enemy.getCurrentAnimationIndex() == 1 )
                {
                    enemy.setCurrentAnimation(0);
                }
            }
            ataqueBoss.restart();
        }

        //Retorna para a animação inicial do Boss
        if(enemy.getCurrentAnimation().isAnimationEnded())
        {
            enemy.setCurrentAnimation(0);
        }
    }
}