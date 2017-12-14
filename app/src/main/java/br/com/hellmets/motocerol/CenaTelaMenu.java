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

public class CenaTelaMenu extends AGScene
{
    //Instância das variáveis (SPRITES)
    AGSprite botao = null;
    AGSprite fundo_inicial = null;
    AGSprite logo = null;

    //Construtor da Classe
    public CenaTelaMenu(AGGameManager manager)
    {
        super(manager);
    }

    @Override
    public void init()
    {
        //Métodos de criação dos Sprites tanto para animação quanto de background
        //Aqui são setados também os parametros de proporção dos Sprites
        fundo_inicial = this.createSprite(R.mipmap.initial_background, 1,1);
        fundo_inicial.setScreenPercent(100,100);

        botao = this.createSprite(R.mipmap.btnjogar, 1,1);
        botao.setScreenPercent(25,15);

        logo = this.createSprite(R.mipmap.titulo, 1,1);
        logo.setScreenPercent(30,30);

        //Configura ALTURA e a LARGURA de todos os sprites na tela do celular
        botao.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        botao.vrPosition.setY(AGScreenManager.iScreenHeight/3);

        fundo_inicial.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        fundo_inicial.vrPosition.setY(AGScreenManager.iScreenHeight/2);

        logo.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        logo.vrPosition.setY(AGScreenManager.iScreenHeight/2);
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
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            this.vrGameManager.vrActivity.finish();
            return;
        }

        //Método que entra no jogo
        //Ao clicar no botão "JOGAR", inicia o jogo
        if (AGInputManager.vrTouchEvents.screenClicked())
        {
            if (botao.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                //Seleciona a cena de JOGO
                this.vrGameManager.setCurrentScene(1);
                return;
            }
        }
    }
}
